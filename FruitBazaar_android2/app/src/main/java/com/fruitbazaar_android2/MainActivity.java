package com.fruitbazaar_android2;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.fruitbazaar_android2.cart.fragment.CartFragment;
import com.fruitbazaar_android2.home.fragment.HomeFragment;
import com.fruitbazaar_android2.model.Fruit;
import com.fruitbazaar_android2.user.fragment.UserFragment;

public class MainActivity extends FragmentActivity {

    private HomeFragment mHomeFragment;
    private CartFragment mCartFragment;
    private UserFragment mUserFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup rgMain = findViewById(R.id.rg_main);
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        switchFragment(mHomeFragment == null ? mHomeFragment = new HomeFragment() : mHomeFragment);
                        break;
                    case R.id.rb_cart:
                        switchFragment(mCartFragment == null ? mCartFragment = new CartFragment() : mCartFragment);
                        break;
                    case R.id.rb_user:
                        switchFragment(mUserFragment == null ? mUserFragment = new UserFragment() : mUserFragment);
                        break;
                }
            }
        });

        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment fragment) {
        if (mCurrentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded()) {
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.add(R.id.frameLayout, fragment).commit();
            } else {
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                transaction.show(fragment).commit();
            }
            mCurrentFragment = fragment;
        }
    }

    public CartFragment getCartFragment() {
        return mCartFragment;
    }

    public void addFruitToCart(Fruit fruit) {
        if (mCartFragment != null) {
            mCartFragment.addFruit(fruit);
            // 确保CartFragment刷新其ListView
            mCartFragment.refreshListView();
        }
    }
}
