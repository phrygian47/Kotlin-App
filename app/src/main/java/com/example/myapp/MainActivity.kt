package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var foodDiaryAdapter: FoodDiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foodDiaryAdapter = FoodDiaryAdapter(mutableListOf())

        rvTodoItems.adapter = foodDiaryAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddFood.setOnClickListener {
            val newFood = etFood.text.toString()
            val newCalories = etCalories.text.toString()
            var totalCalories = 0
            if(newFood.isNotEmpty()) {
                val food = Food(newFood, newCalories, isChecked = false)
                foodDiaryAdapter.addFood(food)
                etFood.text.clear()
                etCalories.text.clear()
            }
            for (food in foodDiaryAdapter.getFoodList()){
                totalCalories += food.calories.toInt()
            }
            tvTotalCalories.text = totalCalories.toString()
        }
        btnClear.setOnClickListener {
            foodDiaryAdapter.clearDiary()
        }
        btnSelectAll.setOnClickListener {
            foodDiaryAdapter.selectAll()
        }
    }
}