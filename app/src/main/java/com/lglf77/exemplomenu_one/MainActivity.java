package com.lglf77.exemplomenu_one;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lglf77.exemplomenu_one.library.ChatFragment;
import com.lglf77.exemplomenu_one.library.CurvedBottomNavigationView;
import com.lglf77.exemplomenu_one.library.PrincipalFragment;
import com.lglf77.exemplomenu_one.library.ShoppingFragment;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout frameLayout;

    View fragmentShowPrincipal;

    VectorMasterView btnIconVectorChat, btnIconVectorPrincipal, btnIconVectorShopping;
    LinearLayout linearContainerButtons;

    CurvedBottomNavigationView bottomNavigationView;
    Animation animationShowFragmentPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
    }

    private void initViews() {

        // Frame Layout de chamada dos 3 fragmentos
        frameLayout = findViewById(R.id.frame_layout_geral);

        // Fragmento principal, relacionado ao botão central do Menu
        fragmentShowPrincipal = findViewById(R.id.fragment_show_principal);

        // Animação de entrada do Fragmento ao iniciar a Atividade Principal do Menu
        animationShowFragmentPrincipal = AnimationUtils.loadAnimation(
                this, R.anim.fade_in);
        fragmentShowPrincipal.setAnimation(animationShowFragmentPrincipal);

        // View
        bottomNavigationView = findViewById(R.id.curved_bottom_navigation_view);
        linearContainerButtons = findViewById(R.id.linear_container_buttons);

        // Método de inflar o CurvedBottomNavigation
        bottomNavigationView.inflateMenu(R.menu.bottom_navigation_menu);
        // bottomNavigationView.setSelectedItemId(R.id.action_principal);

        // Definir evento para navegação dos Botões por case switch
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void revertAnimationButtonFab() {

        // Método de chamada do OnBackPressed do celular
        linearContainerButtons.animate().translationX(0).setDuration(500).setStartDelay(100);
        linearContainerButtons.animate().translationY(0).setDuration(500).setStartDelay(100);
        linearContainerButtons.setVisibility(View.VISIBLE);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;

        switch (menuItem.getItemId()) {
            case R.id.action_chat:
                selectedFragment = new ChatFragment();
                draw(6);
                linearContainerButtons.animate().translationX(0).setDuration(2000).setStartDelay(500);
                linearContainerButtons.animate().translationY(90).setDuration(2000).setStartDelay(500);
                break;

            case R.id.action_principal:
                selectedFragment = new PrincipalFragment();
                draw(2);
                break;

            case R.id.action_shoopping:
                selectedFragment = new ShoppingFragment();
                draw();
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.frame_layout_geral, Objects.requireNonNull(selectedFragment)).commit();

        return true;
    }

    private void draw() {
        bottomNavigationView.mFirstCurveStartPoint.set(
                (bottomNavigationView.mNavigationBarWidth * 10 / 12)
                        - (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        - (bottomNavigationView.CURVE_CICLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveEndPoint.set(
                bottomNavigationView.mNavigationBarWidth * 10 / 12,
                bottomNavigationView.CURVE_CICLE_RADIUS
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 4));

        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set(
                (bottomNavigationView.mNavigationBarWidth * 10 / 12)
                        + (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveControlPoint1.set(
                bottomNavigationView.mFirstCurveStartPoint.x
                        + bottomNavigationView.CURVE_CICLE_RADIUS
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 4),
                bottomNavigationView.mFirstCurveStartPoint.y);

        bottomNavigationView.mFirstCurveControlPoint2.set(
                bottomNavigationView.mFirstCurveEndPoint.x -
                        (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        + bottomNavigationView.CURVE_CICLE_RADIUS,
                bottomNavigationView.mFirstCurveEndPoint.y);

        // Mesmo com o segundo
        bottomNavigationView.mSecondCurveControlPoint1.set(
                bottomNavigationView.mSecondCurveStartPoint.x
                        + (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        - bottomNavigationView.CURVE_CICLE_RADIUS,
                bottomNavigationView.mSecondCurveStartPoint.y);
        bottomNavigationView.mSecondCurveControlPoint2.set(
                bottomNavigationView.mSecondCurveEndPoint.x -
                        (bottomNavigationView.CURVE_CICLE_RADIUS
                                + (bottomNavigationView.CURVE_CICLE_RADIUS / 4)),
                bottomNavigationView.mSecondCurveEndPoint.y);

    }

    private void draw(int i) {

        bottomNavigationView.mFirstCurveStartPoint.set(
                (bottomNavigationView.mNavigationBarWidth / i)
                        - (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        - (bottomNavigationView.CURVE_CICLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveEndPoint.set(
                bottomNavigationView.mNavigationBarWidth / i,
                bottomNavigationView.CURVE_CICLE_RADIUS
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 4));

        bottomNavigationView.mSecondCurveStartPoint = bottomNavigationView.mFirstCurveEndPoint;
        bottomNavigationView.mSecondCurveEndPoint.set(
                (bottomNavigationView.mNavigationBarWidth / i)
                        + (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 3), 0);

        bottomNavigationView.mFirstCurveControlPoint1.set(
                bottomNavigationView.mFirstCurveStartPoint.x
                        + bottomNavigationView.CURVE_CICLE_RADIUS
                        + (bottomNavigationView.CURVE_CICLE_RADIUS / 4),
                bottomNavigationView.mFirstCurveStartPoint.y);

        bottomNavigationView.mFirstCurveControlPoint2.set(
                bottomNavigationView.mFirstCurveEndPoint.x -
                        (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        + bottomNavigationView.CURVE_CICLE_RADIUS,
                bottomNavigationView.mFirstCurveEndPoint.y);

        //Same with second
        bottomNavigationView.mSecondCurveControlPoint1.set(
                bottomNavigationView.mSecondCurveStartPoint.x
                        + (bottomNavigationView.CURVE_CICLE_RADIUS * 2)
                        - bottomNavigationView.CURVE_CICLE_RADIUS,
                bottomNavigationView.mSecondCurveStartPoint.y);
        bottomNavigationView.mSecondCurveControlPoint2.set(
                bottomNavigationView.mSecondCurveEndPoint.x -
                        (bottomNavigationView.CURVE_CICLE_RADIUS
                                + (bottomNavigationView.CURVE_CICLE_RADIUS / 4)),
                bottomNavigationView.mSecondCurveEndPoint.y);
    }

    @Override
    public void onBackPressed() {
        revertAnimationButtonFab();
    }

}