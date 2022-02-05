package com.kh69.contentformathquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var mTvQuestion: EditText
    companion object {
        fun  getValue(): String{
            return "Given that \$x=\\theta-\\sin\\theta\$ and \$y=1-\\cos\\theta\$, Show that \$\\dfrac{dy}{dx}=\\cot\\dfrac{\\theta}{2}\$.\n" +
                    "    \n" +
                    "    \\textbf{\\textit{Solution}}\n" +
                    "    \n" +
                    "    \$x=\\theta-\\sin \\theta\$\n" +
                    "    \n" +
                    "    \$\\dfrac{d x}{d \\theta}-1-\\cos 0\$\n" +
                    "    \n" +
                    "    \$y=1-\\cos \\theta\$\n" +
                    "    \n" +
                    "    \$\\dfrac{d y}{d \\theta}=\\sin \\theta\$\n" +
                    "    \n" +
                    "    \$\\begin{array}{lll}\n" +
                    "         \\dfrac{d y}{d x} & = & \\dfrac{d y}{d \\theta} \\cdot \\dfrac{d \\theta}{d x} \\\\\\\\\n" +
                    "         & = &\\sin \\theta \\cdot \\dfrac{1}{1-\\cos \\theta} \\\\\\\\\n" +
                    "        & = &\\dfrac{\\sin \\theta}{1-\\cos \\theta} \\\\\\\\\n" +
                    "        & = &\\dfrac{\\sin \\theta(1+\\cos \\theta)}{(1-\\cos \\theta)(1+\\cos \\theta)} \\\\\\\\\n" +
                    "        & = &\\dfrac{\\sin (1+\\cos \\theta)}{1-\\cos ^{2} \\theta} \\\\\\\\\n" +
                    "        & = &\\dfrac{\\sin \\theta(1+\\cos \\theta)}{\\sin ^{2} \\theta} \\\\\\\\\n" +
                    "        & = &\\dfrac{1+\\cos \\theta}{\\sin \\theta}\\\\\\\\\n" +
                    "        & = &\\frac{1+2 \\cos ^{2} \\frac{\\theta}{2}-1}{2 \\sin \\frac{\\theta}{2} \\cos \\frac{\\theta}{2}} \\\\\\\\\n" +
                    "        & = &\\dfrac{\\cos \\frac{\\theta}{2}}{\\sin \\frac{\\theta}{2}}=\\cot \\frac{\\theta}{2}\n" +
                    "        \\end{array}\$"

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTvQuestion = findViewById(R.id.tv_katex)
//        val question = "Given that \$x=\\theta-\\sin\\theta\$ and \$y=1-\\cos\\theta\$, Show that \$\\dfrac{dy}{dx}=\\cot\\dfrac{\\theta}{2}\$.\n" +
//                "    \n" +
//                "    \\textbf{\\textit{Solution}}\n" +
//                "    \n" +
//                "    \$x=\\theta-\\sin \\theta\$\n" +
//                "    \n" +
//                "    \$\\dfrac{d x}{d \\theta}-1-\\cos 0\$\n" +
//                "    \n" +
//                "    \$y=1-\\cos \\theta\$\n" +
//                "    \n" +
//                "    \$\\dfrac{d y}{d \\theta}=\\sin \\theta\$\n" +
//                "    \n" +
//                "    \$\\begin{array}{lll}\n" +
//                "         \\dfrac{d y}{d x} & = & \\dfrac{d y}{d \\theta} \\cdot \\dfrac{d \\theta}{d x} \\\\\\\\\n" +
//                "         & = &\\sin \\theta \\cdot \\dfrac{1}{1-\\cos \\theta} \\\\\\\\\n" +
//                "        & = &\\dfrac{\\sin \\theta}{1-\\cos \\theta} \\\\\\\\\n" +
//                "        & = &\\dfrac{\\sin \\theta(1+\\cos \\theta)}{(1-\\cos \\theta)(1+\\cos \\theta)} \\\\\\\\\n" +
//                "        & = &\\dfrac{\\sin (1+\\cos \\theta)}{1-\\cos ^{2} \\theta} \\\\\\\\\n" +
//                "        & = &\\dfrac{\\sin \\theta(1+\\cos \\theta)}{\\sin ^{2} \\theta} \\\\\\\\\n" +
//                "        & = &\\dfrac{1+\\cos \\theta}{\\sin \\theta}\\\\\\\\\n" +
//                "        & = &\\frac{1+2 \\cos ^{2} \\frac{\\theta}{2}-1}{2 \\sin \\frac{\\theta}{2} \\cos \\frac{\\theta}{2}} \\\\\\\\\n" +
//                "        & = &\\dfrac{\\cos \\frac{\\theta}{2}}{\\sin \\frac{\\theta}{2}}=\\cot \\frac{\\theta}{2}\n" +
//                "        \\end{array}\$"
//        mTvQuestion.setText(question)

        val ds = " Find the values of \$\\mathrm{k}\$ for which the equation \$\\dfrac{x^{2}-x+1}{x-1}\$ has repeated roots. What are the repeated roots? ";

    }
}