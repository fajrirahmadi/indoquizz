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
import com.mikepenz.fastadapter.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProfileMenuAdapter extends AbstractItem<ProfileMenuAdapter, ProfileMenuAdapter.ViewHolder> {

    private int icon;
    private String label;

    public ProfileMenuAdapter(int icon, String label) {
        this.icon = icon;
        this.label = label;
    }

    @Override
    public int getType() {
        return R.id.profile_menu_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_profile_menu;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NotNull View v) {
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.profileMenuIcon)
        AppCompatImageView profileMenuIcon;
        @BindView(R.id.profileMenuLabel)
        AppCompatTextView profileMenuLabel;
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
        holder.profileMenuIcon.setBackgroundResource(icon);
        holder.profileMenuLabel.setText(label);
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.profileMenuIcon.setBackgroundResource(0);
        holder.profileMenuLabel.setText(null);
    }
}
