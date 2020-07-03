package ge.btu.android_final.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import ge.btu.android_final.R
import ge.btu.android_final.adapter.DishLocationsAdapter
import ge.btu.android_final.api.DishesEndpoints
import ge.btu.android_final.api.ServiceBuilder
import ge.btu.android_final.model.Data
import ge.btu.android_final.model.Dishes
import ge.btu.android_final.model.Location
import kotlinx.android.synthetic.main.activity_dish_locations.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishLocationsActivity : AppCompatActivity() {

    private lateinit var adapter: DishLocationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_locations)
        init()
    }

    private fun init() {
        var item:Data? = intent.getParcelableExtra("dishItem")
        if (item != null) {
            getDishLocations(item.dishnameEn)
        }
    }

    private fun dishLocationsContent(body: List<Location>, dishName: String) {
        adapter = DishLocationsAdapter()
        adapter.submitItems(body)
        dishNameView.text = dishName.toString()
        dishLocationsRecyclerView.adapter = adapter
        dishLocationsRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        dishLocationsRecyclerView.dishNameView.text = dishName.toString()
    }

    private fun getDishLocations(dishName: String) {
        val service = ServiceBuilder.buildService(DishesEndpoints::class.java)
        val request = service.getDishLocations(dishName)
        request.enqueue(object : Callback<Dishes> {
            override fun onResponse(call: Call<Dishes>, response: Response<Dishes>) {
                if (response.isSuccessful) {
                    var body = response?.body()
                    if (body != null) {

                        var locations: List<Location> = body.data[0].locations
                        dishLocationsContent(locations, dishName)
                    }
                }
            }
            override fun onFailure(call: Call<Dishes>, t: Throwable) {
                Toast.makeText(applicationContext, "Error Reading JSON", Toast.LENGTH_LONG).show()
            }
        })
    }
}
