package com.fruitbazaar_android2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.fruitbazaar_android2.MainActivity;
import com.fruitbazaar_android2.R;
import com.fruitbazaar_android2.model.Fruit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    private boolean isCart;
    private List<Boolean> checkedList; // 记录每个项的选择状态


    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects, boolean isCart) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
        this.isCart = isCart;
        this.checkedList = new ArrayList<>(Collections.nCopies(objects.size(), false)); // 初始化选择状态列表
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的 Fruit 实例

        if (convertView == null) {
            if (isCart) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
                CheckBox checkBox = convertView.findViewById(R.id.checkbox);
                TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);

                // 添加空列表检查
                if (checkedList != null && position < checkedList.size()) {
                    checkBox.setChecked(checkedList.get(position));
                }

                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

                    // 添加空列表检查
                    if (checkedList != null && position < checkedList.size()) {
                        checkedList.set(position, isChecked); // 更新选择状态列表
                    }
                });
                if (fruit != null) {
                    tvQuantity.setText("数量: " + fruit.getQuantity());
                }

            } else {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                Button btnAdd = convertView.findViewById(R.id.btnAdd);
                btnAdd.setOnClickListener(v -> {
                    FragmentActivity activity = (FragmentActivity) getContext();
                    if (activity instanceof MainActivity && fruit != null) {
                        ((MainActivity) activity).addFruitToCart(fruit);
                    }
                });
            }
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvOrigin = convertView.findViewById(R.id.tvOrigin);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);
        ImageView tvFruitImage = convertView.findViewById(R.id.tvFruitImage);

        if (fruit != null) {
            tvName.setText(fruit.getName());
            tvPrice.setText("价格: " + fruit.getNewPrice() + " 元");
            tvOrigin.setText("产地: " + fruit.getOrigin());
            tvDescription.setText("描述: " + fruit.getDesctext());
            String imgName = fruit.getImg();
            Log.e("FruitImage", "Image Name: " + imgName);
            int imageResourceId = getContext().getResources().getIdentifier(imgName, "drawable", getContext().getPackageName());

            Log.e("imageResourceId", "imageResourceId : " + imageResourceId);
            if (imageResourceId != 0) {
                // 如果找到了资源 ID，设置给 ImageView
                tvFruitImage.setImageResource(imageResourceId);
            } else {
                // 如果没有找到，则设置一个默认图片
                tvFruitImage.setImageResource(R.drawable.cm);
            }

        }
        return convertView;
    }
}
