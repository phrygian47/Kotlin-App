package com.example.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_todo.view.*

class FoodDiaryAdapter (
    private val foods: MutableList<Food>
    ) : RecyclerView.Adapter<FoodDiaryAdapter.DiaryViewHolder>() {

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addFood(food: Food) {
        foods.add(food)
        notifyItemInserted(foods.size-1)
    }

    fun clearDiary() {
        foods.removeAll {food: Food ->
            food.isChecked
        }
        notifyDataSetChanged()
    }

    fun selectAll(){
        for (food in foods){
            if (!food.isChecked){
                food.isChecked = true
            }
        }
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val currFood = foods[position]
        holder.itemView.apply {
            tvFood.text = currFood.title
            tvCalories.text = currFood.calories
            cbDone.isChecked = currFood.isChecked
            cbDone.setOnCheckedChangeListener { _, _ ->
                currFood.isChecked = !currFood.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    fun getFoodList(): List<Food>{
        return foods
    }
}