package co.id.indoquizz.objects.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.indoquizz.R;
import co.id.indoquizz.objects.User;
import co.id.indoquizz.utils.image.GlideUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends AbstractItem<UserAdapter, UserAdapter.ViewHolder> {

    private User user;

    public UserAdapter(User user) {
        this.user = user;
    }

    @Override
    public int getType() {
        return R.id.user_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_user;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NotNull View v) {
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profilePictureImageView)
        AppCompatImageView profilePictureImageView;
        @BindView(R.id.profileGradeImageView)
        AppCompatImageView profileGradeImageView;
        @BindView(R.id.userNameTextView)
        AppCompatTextView userNameTextView;
        @BindView(R.id.buttonAddFriend)
        public AppCompatImageView buttonAddFriend;
        @BindView(R.id.buttonLikeFriend)
        AppCompatImageView buttonLikeFriend;
        @BindView(R.id.sendQuizButton)
        public AppCompatButton sendQuizButton;
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
        holder.userNameTextView.setText(user.getName().split(" ")[0]);
        if (StringUtils.isNotBlank(user.getUrlImage()))
            GlideUtils.Companion.setImageCircle(holder.context, user.getUrlImage(), holder.profilePictureImageView);
        else
            GlideUtils.Companion.setImageCircle(holder.context, R.mipmap.ic_launcher, holder.profilePictureImageView);
        holder.profileGradeImageView.setImageResource(user.getGrade().getIcon());
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.profilePictureImageView.setImageResource(0);
        holder.profileGradeImageView.setImageResource(0);
        holder.userNameTextView.setText(null);
    }
}
