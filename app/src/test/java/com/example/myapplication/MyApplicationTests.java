package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.test.core.app.ActivityScenario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class MyApplicationTests {

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    private ActivityScenario<MainActivity> mainActivityScenario;
    private ActivityScenario<instrucoes> instrucoesActivityScenario;
    private ActivityScenario<JogoPersonagem> jogoPersonagemActivityScenario;
    private ActivityScenario<JogoPersonagem2> jogoPersonagem2ActivityScenario;
    private ActivityScenario<Jogos> jogosActivityScenario;
    private ActivityScenario<JogosFiltro> jogosFiltroActivityScenario;

    @Before
    public void setUp() {
        mainActivityScenario = ActivityScenario.launch(MainActivity.class);
        instrucoesActivityScenario = ActivityScenario.launch(instrucoes.class);
        jogoPersonagemActivityScenario = ActivityScenario.launch(JogoPersonagem.class);
        jogoPersonagem2ActivityScenario = ActivityScenario.launch(JogoPersonagem2.class);
        jogosActivityScenario = ActivityScenario.launch(Jogos.class);
        jogosFiltroActivityScenario = ActivityScenario.launch(JogosFiltro.class);

        
        Mockito.when(mockSharedPreferences.getBoolean("lastActivity", false)).thenReturn(false);
        Mockito.when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        Mockito.when(mockEditor.putBoolean(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(mockEditor);
    }

    @After
    public void tearDown() {
        mainActivityScenario.close();
        instrucoesActivityScenario.close();
        jogoPersonagemActivityScenario.close();
        jogoPersonagem2ActivityScenario.close();
        jogosActivityScenario.close();
        jogosFiltroActivityScenario.close();
    }

    @Test
    public void testMainActivityOrientation() {
        mainActivityScenario.onActivity(activity -> {
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, activity.getRequestedOrientation());
        });
    }

    @Test
    public void testInstrucoesActivityOrientation() {
        instrucoesActivityScenario.onActivity(activity -> {
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, activity.getRequestedOrientation());
        });
    }

    @Test
    public void testJogoPersonagem2ActivityOrientation() {
        jogoPersonagem2ActivityScenario.onActivity(activity -> {
            assertEquals(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, activity.getRequestedOrientation());
        });
    }

    @Test
    public void testInstrucoesBackButton() {
        instrucoesActivityScenario.onActivity(activity -> {
            Button backButton = activity.findViewById(R.id.back_button);
            assertNotNull(backButton);
            assertEquals(View.VISIBLE, backButton.getVisibility());
        });
    }

    @Test
    public void testJogoPersonagemBackButton() {
        jogoPersonagemActivityScenario.onActivity(activity -> {
            ImageButton backButton = activity.findViewById(R.id.back_button);
            assertNotNull(backButton);
            assertEquals(View.VISIBLE, backButton.getVisibility());
        });
    }

    @Test
    public void testJogosFiltroPreferences() {
        jogosFiltroActivityScenario.onActivity(activity -> {
            
            SharedPreferences preferences = mockSharedPreferences;
            assertNotNull(preferences);
            boolean lastWasPersonagem1 = preferences.getBoolean("lastActivity", false);
            assertFalse(lastWasPersonagem1);
        });
    }
}
