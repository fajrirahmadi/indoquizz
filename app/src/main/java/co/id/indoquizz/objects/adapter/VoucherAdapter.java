package co.id.indoquizz.objects.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.indoquizz.R;
import co.id.indoquizz.utils.image.GlideUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoucherAdapter extends AbstractItem<VoucherAdapter, VoucherAdapter.ViewHolder> {

    private int icon;
    private boolean isComplete;

    public VoucherAdapter(int icon, boolean isComplete) {
        this.icon = icon;
        this.isComplete = isComplete;
    }

    @Override
    public int getType() {
        return R.id.voucher_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_promo;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NotNull View v) {
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.promoBanner)
        AppCompatImageView promoBanner;
        @BindView(R.id.claimButton)
        public AppCompatButton claimButton;
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
        GlideUtils.Companion.setImage(holder.context, icon, holder.promoBanner);
        if (isComplete) {
            holder.claimButton.setVisibility(View.VISIBLE);
        } else {
            holder.claimButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);
        holder.promoBanner.setBackgroundResource(0);
    }
}
