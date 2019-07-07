package co.id.indoquizz.objects.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.indoquizz.R;
import co.id.indoquizz.objects.QuestionType;
import co.id.indoquizz.objects.Quiz;
import co.id.indoquizz.utils.image.GlideUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class QuizMainAdapter extends AbstractItem<QuizMainAdapter, QuizMainAdapter.ViewHolder> {

    private Quiz quiz;
    private String uid;
    private List<String> answer;

    public QuizMainAdapter(Quiz quiz, String uid) {
        this.quiz = quiz;
        this.uid = uid;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public List<String> getAnswer() {
        return answer;
    }

    @Override
    public int getType() {
        return R.id.quiz_main_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_main_quiz;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NotNull View v) {
        return new ViewHolder(v);
    }

    public void setUserAnswered(@NotNull String uid) {
        quiz.getUserAnswered().put(uid, true);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userPostImageView)
        AppCompatImageView userPostImageView;
        @BindView(R.id.userNameTextView)
        AppCompatTextView userNameTextView;
        @BindView(R.id.imageQuestionTextView)
        AppCompatImageView imageQuestionTextView;
        @BindView(R.id.loveButton)
        AppCompatImageView loveButton;
        @BindView(R.id.shareButton)
        public AppCompatImageView shareButton;
        @BindView(R.id.questionTextView)
        AppCompatTextView questionTextView;
        @BindView(R.id.firstAnswerTextView)
        public AppCompatButton firstAnswerTextView;
        @BindView(R.id.secondAnswerTextView)
        public AppCompatButton secondAnswerTextView;
        @BindView(R.id.thirdAnswerTextView)
        public AppCompatButton thirdAnswerTextView;
        @BindView(R.id.forthAnswerTextView)
        public AppCompatButton forthAnswerTextView;
        @BindView(R.id.areaSecond)
        LinearLayout areaSecond;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }
    }

    @Override
    public void bindView(@NonNull ViewHolder holder,
                         @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);
        if (QuestionType.TRUEFALSE == quiz.getQuestionType()) {
            holder.areaSecond.setVisibility(View.GONE);
        } else {
            holder.areaSecond.setVisibility(View.VISIBLE);
        }
        GlideUtils.Companion.setImageCircle(holder.context, R.mipmap.ic_launcher, holder.userPostImageView);
        if (StringUtils.isNotBlank(quiz.getUrlImage()))
            GlideUtils.Companion.setImage(holder.context, quiz.getUrlImage(), holder.imageQuestionTextView);
        else
            GlideUtils.Companion.setImage(holder.context, R.drawable.promo_02, holder.imageQuestionTextView);
        holder.questionTextView.setText(quiz.getQuestion());
        answer = new ArrayList<>(quiz.getMapAnswer().keySet());
        if (answer.size() > 0)
            holder.firstAnswerTextView.setText(answer.get(0));
        if (answer.size() > 1)
            holder.secondAnswerTextView.setText(answer.get(1));
        if (answer.size() > 2)
            holder.thirdAnswerTextView.setText(answer.get(2));
        if (answer.size() > 3)
            holder.forthAnswerTextView.setText(answer.get(3));
        if (quiz.getUserLoved().containsKey(uid))
            holder.loveButton.setImageResource(R.drawable.ic_like);
        else
            holder.loveButton.setImageResource(R.drawable.ic_unlike);
        holder.loveButton.setOnClickListener(view -> {
            if (quiz.getUserLoved().containsKey(uid)) {
                quiz.getUserLoved().remove(uid);
                holder.loveButton.setImageResource(R.drawable.ic_unlike);
            } else {
                quiz.getUserLoved().put(uid, true);
                holder.loveButton.setImageResource(R.drawable.ic_like);
            }
        });
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.questionTextView.setText(null);
        holder.userNameTextView.setText(null);
        holder.firstAnswerTextView.setText(null);
        holder.secondAnswerTextView.setText(null);
        holder.thirdAnswerTextView.setText(null);
        holder.forthAnswerTextView.setText(null);
    }
}
