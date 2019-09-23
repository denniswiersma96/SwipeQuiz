package com.example.swipequiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question.view.*

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question : Question) {
            itemView.tvQuestion.text = question.text
        }
    }
}