package ge.btu.android_final.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import ge.btu.android_final.DishItemOnClickListener
import ge.btu.android_final.R
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
        isUserLoggedIn()
        val service = ServiceBuilder.buildService(DishesEndpoints::class.java)
        val request = service.getDishes()
        request.enqueue(object : Callback<Dishes> {
            override fun onResponse(call: Call<Dishes>, response: Response<Dishes>) {
                if (response.isSuccessful) {
                    recycler_view.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        dishesAdapter = DishesRecyclerAdapter(object :
                            DishItemOnClickListener {
                            override fun clickEventHandler(item: Data) {
                                val i = Intent(this@MainActivity, DishLocationsActivity::class.java)
                                i.putExtra("dishItem",item)
                                startActivity(i)
                            }
                        })
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

    private fun isUserLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menuSignOut -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "თქვენ გახვედით ანგარიშიდან", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
