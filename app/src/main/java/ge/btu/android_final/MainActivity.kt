package ge.btu.android_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ge.btu.android_final.adapter.DishesRecyclerAdapter
import ge.btu.android_final.api.DishesEndpoints
import ge.btu.android_final.api.ServiceBuilder
import ge.btu.android_final.model.Data
import ge.btu.android_final.model.Dishes
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var dishesAdapter: DishesRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val service = ServiceBuilder.buildService(DishesEndpoints::class.java)
        val request = service.getDishes()
        request.enqueue(object : Callback<Dishes> {
            override fun onResponse(call: Call<Dishes>, response: Response<Dishes>) {
                if (response.isSuccessful) {
                    recycler_view.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        dishesAdapter = DishesRecyclerAdapter()
                        dishesAdapter.setItems(response.body()?.data as ArrayList<Data>);
                        adapter = dishesAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Dishes>, t: Throwable) {
                Log.d("Dishes error", t.toString())
                Toast.makeText(
                    this@MainActivity,
                    "დაფიქსირდა შეცდომა, გთხოვთ სცადოთ თავიდან",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
