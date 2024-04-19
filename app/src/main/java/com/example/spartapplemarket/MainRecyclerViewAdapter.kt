package com.example.spartapplemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spartapplemarket.databinding.ItemProductBinding
import java.text.DecimalFormat

//뷰홀더 패턴 알아보기
class MainRecyclerViewAdapter(private val productList : MutableList<AppleItem>) :RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(position : Int)
    }

    var itemClick : ItemClick? = null


    inner class ViewHolder(binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
        val tv_Title = binding.tvTitle
        val tv_price = binding.tvPrice
        val tv_location = binding.tvLocation
        val iv_product = binding.ivProduct
        val btn_message = binding.btnMessage
        val btn_like = binding.btnLike

        //루트 레이아웃
        val root = binding.root
    }

    //뷰홀더 만들기
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return productList.size
    }

    //뷰홀더와 데이터 묶기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productData = productList[position]
        val dec = DecimalFormat("#,###")

        holder.tv_Title.text = productData.title
        holder.tv_price.text = "${dec.format(productData.price)}원"
        holder.tv_location.text = productData.location
        holder.iv_product.setImageResource(productData.picture)
        holder.btn_message.text = productData.comment.toString()
        holder.btn_like.text = productData.like.toString()

        holder.iv_product.clipToOutline = true

        holder.itemView.setOnClickListener {
            itemClick?.onClick(position)
        }

    }

}