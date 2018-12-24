package com.pereved.schedule.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondarySwitchDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pereved.schedule.R;
import com.pereved.schedule.Schedule.ScheduleFragment;
import com.pereved.schedule.Teachers.PeoplesFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static Drawer result = null;
    private Drawer resultAppended = null;
    @BindDrawable(R.drawable.ic_schedule)
    Drawable ic_schedule;
    @BindDrawable(R.drawable.ic_pen)
    Drawable ic_pen;
    @BindDrawable(R.drawable.ic_ass)
    Drawable ic_ass;
    @BindDrawable(R.drawable.ic_book)
    Drawable ic_book;
    @BindDrawable(R.drawable.ic_log)
    Drawable ic_log;
    @BindDrawable(R.drawable.ic_teacher)
    Drawable ic_teacher;
    @BindDrawable(R.drawable.ic_ring)
    Drawable ic_ring;
    @BindDrawable(R.drawable.ic_palette)
    Drawable ic_palette;
    @BindDrawable(R.drawable.ic_settings)
    Drawable ic_settings;
    @BindDrawable(R.drawable.ic_about)
    Drawable ic_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setupNavigationDrawer(savedInstanceState);

        replaceFragment(new ScheduleFragment());
    }

    public void replaceFragment(Fragment selectedFragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container,
                selectedFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupNavigationDrawer(Bundle savedInstanceState) {
        result = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.header)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Расписание").withIcon(ic_schedule).withBadge("3").withIdentifier(1),
                        new PrimaryDrawerItem().withName("Заметки").withIcon(ic_pen).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Оценки").withIcon(ic_ass).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Учебники").withIcon(ic_book).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Журнал").withIcon(ic_log).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Люди").withIcon(ic_teacher).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Звонки").withIcon(ic_ring).withIdentifier(7),
                        new SectionDrawerItem().withName("Прочее"),
                        new SecondarySwitchDrawerItem().withName("Тёмная тема").withIcon(ic_palette).withSelectable(false).withIdentifier(8),
                        new SecondaryDrawerItem().withName("Настройки").withIcon(ic_settings).withIdentifier(9),
                        new SecondaryDrawerItem().withName("О приложении").withIcon(ic_about).withEnabled(false).withIdentifier(10)
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github).withBadge("12").withIdentifier(3),
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        Toast.makeText(MainActivity.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        Toast.makeText(MainActivity.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        switch ((int)drawerItem.getIdentifier()) {
                            case 1:
                                replaceFragment(new ScheduleFragment());
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "Учебники", Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                replaceFragment(new PeoplesFragment());
                            default:
                                Toast.makeText(MainActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
                                break;
                        }

//                        if(drawerItem != null) {
//                            if(drawerItem instanceof Nameable) {
//                                Toast.makeText(MainActivity.this, ((Nameable)drawerItem).getName().getText(MainActivity.this) + position, Toast.LENGTH_SHORT).show();
//                            }
//
//                            if(drawerItem instanceof Badgeable) {
//                                Badgeable badgeable = (Badgeable)drawerItem;
//                                if(badgeable.getBadge() != null) {
//                                    //note don't do this if your badge contains a "+"
//                                    //only use toString() if you set the test as String
//                                    int badge = Integer.valueOf(badgeable.getBadge().toString());
//                                    if(badge > 0) {
//                                        badgeable.withBadge(String.valueOf(badge - 1));
//                                        result.updateItem(drawerItem);
//                                    }
//                                }
//                            }
//                        }

                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem instanceof SecondaryDrawerItem) {
                            Toast.makeText(MainActivity.this, ((SecondaryDrawerItem)drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        if(drawerView == result.getSlider()) {
                            Log.e("sample", "left opened");
                        } else if(drawerView == resultAppended.getSlider()) {
                            Log.e("sample", "right opened");
                        }
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        if(drawerView == result.getSlider()) {
                            Log.e("sample", "left closed");
                        } else if(drawerView == resultAppended.getSlider()) {
                            Log.e("sample", "right closed");
                        }
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .build();
    }
}