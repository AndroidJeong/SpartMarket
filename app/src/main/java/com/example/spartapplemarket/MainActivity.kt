package com.example.spartapplemarket

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spartapplemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myNotification: MyNotification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<AppleItem>()

        dataList.add(AppleItem(R.drawable.sample1, "산진 한달된 선풍기 팝니다", "서울 서대문구 창천동", 1000, 25, 13))
        dataList.add(AppleItem(R.drawable.sample2, "김치냉장고", "인천 계양구 귤현동", 20000, 28, 8))
        dataList.add(AppleItem(R.drawable.sample3, "샤넬 카드지갑", "수성구 범어동", 10000, 5, 23))
        dataList.add(AppleItem(R.drawable.sample4, "금고", "해운대구 우제2동", 10000, 17, 14))
        dataList.add(AppleItem(R.drawable.sample5, "갤럭시Z플립3 팝니다", "연제구 연산제8동", 150000, 9, 22))
        dataList.add(AppleItem(R.drawable.sample6, "프라다 복조리백", "수원시 영통구 원천동", 50000, 16, 25))
        dataList.add(AppleItem(R.drawable.sample7, "울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장", "남구 옥동", 150000, 54, 142))
        dataList.add(AppleItem(R.drawable.sample8, "샤넬 탑핸들 가방", "동래구 온천제2동", 180000, 7, 31))
        dataList.add(AppleItem(R.drawable.sample9, "4행정 엔진분무기 판매합니다.", "원주시 명륜2동", 30000, 28, 7))
        dataList.add(AppleItem(R.drawable.sample10, "셀린느 버킷 가방", "중구 동화동", 190000, 6, 40))

        val location = resources.getStringArray(R.array.category_array)
        val sipnnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, location)
        binding.mainSpinner.adapter = sipnnerAdapter

        val mainAdapter = MainRecyclerViewAdapter(dataList)
        binding.mainRecyclerView.adapter = mainAdapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayout.VERTICAL
                )
            )
        }

        //onClick 안에 넣으면 안되는 이유?
        val intent = Intent(this, DetailActivity::class.java)

        mainAdapter.itemClick = object : MainRecyclerViewAdapter.ItemClick {
            //val intent = Intent(this, DetailActivity::class.java)
            override fun onClick(position: Int) {
                val list = Product(
                    dataList[position].picture,
                    dataList[position].title,
                    dataList[position].location,
                    dataList[position].price

                )
                intent.putExtra("Product",  list)
                startActivity(intent)
            }
        }


        myNotification = MyNotification(this)

    }

    override fun onResume() {
        super.onResume()

        binding.mainNotification.setOnClickListener {
            myNotification.deliverNotification()
        }

    }


    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setIcon(R.drawable.chat)
        builder.setMessage("정말 종료하시겠습니까?")
        // 버튼 클릭시 이벤트
        val listener = DialogInterface.OnClickListener { _, p0 ->
            if (p0 == DialogInterface.BUTTON_POSITIVE) {
                finish()
            }
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()
    }
}


