package bias.hugoandrade.calendarviewapp.helpers;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperListener {
    void onItemSwipe(int position);
    void onRightClick(int position, RecyclerView.ViewHolder viewHolder);
    void onDragandDrop(int position, RecyclerView.ViewHolder viewHolder);
}