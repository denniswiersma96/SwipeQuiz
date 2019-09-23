package com.example.swipequiz

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.core.view.ViewCompat.setTranslationX
import androidx.core.view.ViewCompat.setAlpha
import android.opengl.ETC1.getWidth
import androidx.recyclerview.widget.ItemTouchHelper


class MainActivity : AppCompatActivity() {

    val questions: ArrayList<Question> = arrayListOf()
    val questionAdapter = QuestionAdapter(questions)
    var swipeBack: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Question.QUESTIONS_TEXT.indices) {
            questions.add(Question(Question.QUESTIONS_TEXT[i], Question.QUESTION_TRUE[i]))
        }

        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedPosition: Int = viewHolder.adapterPosition
                    questionAdapter.notifyItemChanged(viewHolder.adapterPosition)

                    val leftDirection: Int = 4;
                    val rightDirection: Int = 8;
                    if (direction == rightDirection) checkAnswer(true, swipedPosition)
                    if (direction == leftDirection) checkAnswer(false, swipedPosition)
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    if (actionState === ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive) {
                        viewHolder.itemView.translationX = dX
                    }  else {
                        super.onChildDraw(
                            c, recyclerView, viewHolder, dX, dY,
                            actionState, isCurrentlyActive
                        )
                    }
                }
            }
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvQuestions)
    }

    private fun checkAnswer(chosenValue: Boolean, swipedPosition: Int) {
        var message: String = if (chosenValue and Question.QUESTION_TRUE[swipedPosition])
            "Correct! Nice Job!" else "False! Try Again!"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
