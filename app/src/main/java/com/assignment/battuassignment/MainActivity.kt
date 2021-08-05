package com.assignment.battuassignment

import android.app.Application
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.assignment.battuassignment.databinding.ActivityMainBinding
import com.assignment.battuassignment.ui.database.Dao
import com.assignment.battuassignment.ui.database.MyDatabase
import com.assignment.battuassignment.ui.network.UserDetail
import com.assignment.battuassignment.ui.service.MyService

class MainActivity : AppCompatActivity() ,MyService.CallBack{

    var mService: MyService? = null
    private var mBound: Boolean = false

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var edit : SharedPreferences.Editor
    private lateinit var dataSource : Dao
    var arrayList : List<UserDetail>? = null
    lateinit var adapter : UserAdapter
    lateinit var binding : ActivityMainBinding

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MyService.MyBinder
            mService = binder.getService()
            mService?.setCallBack(this@MainActivity)
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    private lateinit var viewModel: WelcomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val application : Application = this.application

        dataSource  = MyDatabase.getInstance(application).dao

        val viewModelFactory = WelcomeViewModelFactory(dataSource,application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(WelcomeViewModel::class.java)

        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this

        sharedPreferences  = getSharedPreferences("userDetails",0)
        edit = sharedPreferences.edit()



        viewModel.isDataGetSuccessfully.observe(this, Observer {
            if (it){
                arrayList = mService?.arrayList

                viewModel._text.value = "Successful"
            }else{
                viewModel._text.value = "UnSuccessful"
            }
        })

        viewModel.serviceStarted.observe(this, Observer {

            if(it){
                binding.start.visibility = View.GONE
                Intent(this, MyService::class.java).also { intent ->
                    startService(intent)
                }
                viewModel._serviceStarted.value = false
            }

        })


    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
        Intent(this,MyService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        mService?.setCallBack(null)
        unbindService(connection)
        mBound = false
    }

    override fun onsuccess(arrayList: List<UserDetail>) {
        if(!sharedPreferences.getBoolean("isDataBaseCreated",false)){
            viewModel.fillDataToDatabase(arrayList)
            edit.putBoolean("isDataBaseCreated",true)
            edit.apply()

        }
        viewModel._text.value = "Successful"
        adapter = UserAdapter()
        binding.recyclerView.adapter = adapter
        adapter.data = arrayList


    }

    override fun onfailed() {
        binding.start.visibility = View.VISIBLE
        viewModel._text.value = "UnSuccessful"
    }
}