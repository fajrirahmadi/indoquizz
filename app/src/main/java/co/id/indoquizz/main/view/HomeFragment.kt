package co.id.indoquizz.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import co.id.indoquizz.R
import co.id.indoquizz.base.view.BaseFragment
import co.id.indoquizz.objects.QuestionType
import co.id.indoquizz.objects.Quiz
import co.id.indoquizz.objects.adapter.QuizMainAdapter
import co.id.indoquizz.utils.string.StringHelper
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_layout_center_title.*


class HomeFragment : BaseFragment() {

    private val quizMainAdapter = FastItemAdapter<QuizMainAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getInflate(inflater, R.layout.fragment_home, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureEmptyToolbarNoHome(view)
        titleToolbar.text = "QuizIndo"
        configureItemAdapter(quizMainAdapter, quizMainRecycleView)
        quizMainRecycleView.isFocusable = false
        initDummyData()
        handleButtonQuizAdapter()
    }

    private fun handleButtonQuizAdapter() {
        quizMainAdapter.withEventHook(object : ClickEventHook<QuizMainAdapter>() {
            @Nullable
            override fun onBind(@NonNull viewHolder: RecyclerView.ViewHolder?): View? {
                return if (viewHolder is QuizMainAdapter.ViewHolder) {
                    viewHolder.shareButton
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<QuizMainAdapter>,
                item: QuizMainAdapter
            ) {
                shareQuiz(item.quiz.question ?: "")
            }
        })
        quizMainAdapter.withEventHook(object : ClickEventHook<QuizMainAdapter>() {
            @Nullable
            override fun onBind(@NonNull viewHolder: RecyclerView.ViewHolder?): View? {
                return if (viewHolder is QuizMainAdapter.ViewHolder) {
                    viewHolder.firstAnswerTextView
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<QuizMainAdapter>,
                item: QuizMainAdapter
            ) {
                if (item.answer.size > 0)
                    handleAnswer(item.answer[0], item)
            }
        })
        quizMainAdapter.withEventHook(object : ClickEventHook<QuizMainAdapter>() {
            @Nullable
            override fun onBind(@NonNull viewHolder: RecyclerView.ViewHolder?): View? {
                return if (viewHolder is QuizMainAdapter.ViewHolder) {
                    viewHolder.secondAnswerTextView
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<QuizMainAdapter>,
                item: QuizMainAdapter
            ) {
                if (item.answer.size > 1)
                    handleAnswer(item.answer[1], item)
            }
        })
        quizMainAdapter.withEventHook(object : ClickEventHook<QuizMainAdapter>() {
            @Nullable
            override fun onBind(@NonNull viewHolder: RecyclerView.ViewHolder?): View? {
                return if (viewHolder is QuizMainAdapter.ViewHolder) {
                    viewHolder.thirdAnswerTextView
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<QuizMainAdapter>,
                item: QuizMainAdapter
            ) {
                if (item.answer.size > 2)
                    handleAnswer(item.answer[2], item)
            }
        })
        quizMainAdapter.withEventHook(object : ClickEventHook<QuizMainAdapter>() {
            @Nullable
            override fun onBind(@NonNull viewHolder: RecyclerView.ViewHolder?): View? {
                return if (viewHolder is QuizMainAdapter.ViewHolder) {
                    viewHolder.forthAnswerTextView
                } else null
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<QuizMainAdapter>,
                item: QuizMainAdapter
            ) {
                if (item.answer.size > 3)
                    handleAnswer(item.answer[3], item)
            }
        })
    }

    private fun shareQuiz(question: String) {
        val sendIntent = Intent()
        val stringToShare = StringHelper.getStringBuilderToString(
            "Hai, dapatkah Anda membantu saya untuk menjawab quiz ini:\n\n", question,
            "\n\nSaya sedang mendapat tantangan dari QuizIndo, mohon bantuannya."
        )
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, stringToShare)
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun handleAnswer(answer: String, quizMainAdapter: QuizMainAdapter) {
        showDialogWithCancel(
            StringHelper.getStringBuilderToString("Yakin dengan jawaban Anda: ", answer),
            View.OnClickListener {
                infoDialog!!.dismiss()
                if (!quizMainAdapter.quiz.userAnswered.containsKey("uaid")) {
                    if (quizMainAdapter.quiz.mapAnswer[answer] == true)
                        showInfo("Jawaban Anda Benar!")
                    else
                        showInfo("Jawaban Anda Salah!")
                    quizMainAdapter.setUserAnswered("uaid")
                } else {
                    showInfo("Anda telah menjawab pertanyaan ini")
                }
            })
    }

    private fun initDummyData() {
        quizMainAdapter.clear()
        val mapLike = HashMap<String, Boolean>()
        mapLike["uid"] = true
        val map00 = HashMap<String, Boolean>()
        map00["Soeharto"] = false
        map00["Habibie"] = false
        map00["Soekarno"] = true
        map00["Amin Rais"] = false
        val quiz00 = Quiz("Siapakah presiden RI yang pertama?", QuestionType.MULTIPLECHOISE, map00)
        quiz00.userLoved = mapLike
        quizMainAdapter.add(QuizMainAdapter(quiz00, "uid"))
        val map01 = HashMap<String, Boolean>()
        map01["Benar"] = true
        map01["Salah"] = false
        val quiz01 = Quiz("Presiden indonesia ke-7 adalah Joko Widodo?", QuestionType.TRUEFALSE, map01)
        quizMainAdapter.add(QuizMainAdapter(quiz01, "uid"))
        val map02 = HashMap<String, Boolean>()
        map02["Prabowo"] = false
        map02["Jokowi"] = true
        map02["Sandiaga"] = false
        map02["Ahok"] = false
        val quiz02 = Quiz("Siapakah presiden RI yang ke 8?", QuestionType.MULTIPLECHOISE, map02)
        quizMainAdapter.add(QuizMainAdapter(quiz02, "uid"))
    }
}