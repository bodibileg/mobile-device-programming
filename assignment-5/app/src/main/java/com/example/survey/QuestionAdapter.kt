package com.example.survey

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    private var questions: List<SurveyQuestion> = listOf()

    fun setQuestions(questions: List<SurveyQuestion>) {
        this.questions = questions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewQuestion: TextView = itemView.findViewById(R.id.textViewQuestion)
        private val radioGroupOptions: RadioGroup = itemView.findViewById(R.id.radioGroupOptions)

        fun bind(question: SurveyQuestion) {
            textViewQuestion.text = question.title

            radioGroupOptions.removeAllViews()

            for (option in question.options) {
                val radioButton = RadioButton(itemView.context)
                radioButton.text = option
                radioButton.setOnClickListener {
                    question.selectedOption = option
                }
                radioGroupOptions.addView(radioButton)
            }

            // Select the previously selected option if any
            question.selectedOption?.let {
                val index = question.options.indexOf(it)
                if (index != -1) {
                    (radioGroupOptions.getChildAt(index) as RadioButton).isChecked = true
                }
            }
        }
    }

    fun getSelectedOptions(): Map<SurveyQuestion, String> {
        val selectedOptionsMap = mutableMapOf<SurveyQuestion, String>()
        for (question in questions) {
            question.selectedOption?.let {
                selectedOptionsMap[question] = it
            }
        }
        return selectedOptionsMap
    }
}