package co.id.indoquizz.objects.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
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

public class FriendAdapter extends AbstractItem<FriendAdapter, FriendAdapter.ViewHolder> {

    private User user;

    public FriendAdapter(User user) {
        this.user = user;
    }

    @Override
    public int getType() {
        return R.id.friend_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_friend;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NotNull View v) {
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.friendPrictureImageView)
        AppCompatImageView friendPrictureImageView;
        @BindView(R.id.friendNameTextView)
        AppCompatTextView friendNameTextView;
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
        holder.friendNameTextView.setText(user.getName().split(" ")[0]);
        if (StringUtils.isNotBlank(user.getUrlImage()))
            GlideUtils.Companion.setImageCircle(holder.context, user.getUrlImage(), holder.friendPrictureImageView);
        else
            GlideUtils.Companion.setImageCircle(holder.context, R.mipmap.ic_launcher, holder.friendPrictureImageView);
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.friendNameTextView.setText(null);
        holder.friendPrictureImageView.setImageResource(0);
    }
}
