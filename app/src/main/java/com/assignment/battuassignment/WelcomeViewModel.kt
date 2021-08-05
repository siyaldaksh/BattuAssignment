package com.assignment.battuassignment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.battuassignment.ui.database.*
import com.assignment.battuassignment.ui.network.ApiUtil
import com.assignment.battuassignment.ui.network.UserDetail
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeViewModel (private val dataSource: Dao,
                        application: Application
)
: AndroidViewModel(application) {

    private var job = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    var _serviceStarted = MutableLiveData<Boolean>()
    val serviceStarted : LiveData<Boolean>
        get() = _serviceStarted

    var _isDataGetSuccessfully = MutableLiveData<Boolean>()
    val isDataGetSuccessfully : LiveData<Boolean>
        get() = _isDataGetSuccessfully

    var _text = MutableLiveData<String>()
    val text : LiveData<String>
        get() = _text

  //  var arrayList : List<UserDetail>? = null

    init {
        _text.value = "Welcome"
    }

    fun serviceStarted(){
        _serviceStarted.value = true
    }

   /* fun gettingDataFromServer(){

        val apiService = ApiUtil.getUi()

        val call = apiService.getUiData()

        call.enqueue(object : Callback<List<UserDetail>> {
            override fun onResponse(
                call: Call<List<UserDetail>>,
                response: Response<List<UserDetail>>
            )
            {
                arrayList = response.body()
                _isDataGetSuccessfully.value = true
                _text.value = "Successful"

            }

            override fun onFailure(call: Call<List<UserDetail>>, t: Throwable) {

                _isDataGetSuccessfully.value = false
                _text.value = "UnSuccessful"
            }

        })
    }
*/
    fun fillDataToDatabase(arrayList : List<UserDetail>){
        uiScope.launch {

            arrayList.let {

                for(i:Int in 0 until it.size){

                    val userData = DataClass(it[i].id,it[i].name,it[i].username,it[i].email,it[i].phone,it[i].website)
                    val address = AddressClass(it[i].id,it[i].address.street,it[i].address.suite,it[i].address.city,it[i].address.zipcode)
                    val location = LocationClass(it[i].id,it[i].address.geo.lat,it[i].address.geo.lng)
                    val companyClass =  CompanyClass(it[i].id,it[i].company.name,it[i].company.catchPhrase,it[i].company.bs)

                    insert(userData,address,location,companyClass)
                }
            }
        }

    }

    private suspend fun insert(userData: DataClass,address: AddressClass,locationClass: LocationClass
                               ,companyClass: CompanyClass){
        withContext(Dispatchers.IO){
            dataSource.insertUser(userData)
            dataSource.insertAddress(address)
            dataSource.insertLocation(locationClass)
            dataSource.insertCompany(companyClass)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}