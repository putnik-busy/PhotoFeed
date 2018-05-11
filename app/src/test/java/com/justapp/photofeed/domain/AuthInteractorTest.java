package com.justapp.photofeed.domain;

import com.justapp.photofeed.domain.repository.AuthRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Sergey Rodionov
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthInteractorTest {

    private static final String RESPONSE_DATA = "access_token=12a636fcab5953233706dadacfff3ba8";
    private static final String WRONG_RESPONSE_DATA = "12a636fcab5953233706dadacfff3ba8";
    private static final String TOKEN = "12a636fcab5953233706dadacfff3ba8";

    @Mock
    private AuthRepository mAuthRepository;
    @InjectMocks
    private AuthInteractor mAuthInteractor;

    @Test
    public void saveTokenSuccessParseTokenShouldPass() throws Exception {
        mAuthInteractor.saveToken(RESPONSE_DATA);
        ArgumentCaptor<String> tokenCaptor = ArgumentCaptor.forClass(String.class);
        verify(mAuthRepository).saveToken(tokenCaptor.capture());
        assertThat(tokenCaptor.getValue(), is(TOKEN));
    }

    @Test
    public void saveTokenFailParseTokenShouldPass() throws Exception {
        mAuthInteractor.saveToken(WRONG_RESPONSE_DATA);
        verify(mAuthRepository, never()).saveToken(TOKEN);
    }

    @Test
    public void hasTokenReturnTrueShouldPass() throws Exception {
        when(mAuthRepository.hasToken()).thenReturn(true);
        boolean actual = mAuthInteractor.hasToken();
        assertThat(actual, is(true));
    }

    @Test
    public void hasTokenReturnFalseShouldPass() throws Exception {
        boolean actual = mAuthInteractor.hasToken();
        assertThat(actual, is(false));
    }

    @Test
    public void onLogoffShouldPass() throws Exception {
        mAuthInteractor.onLogoff();
        verify(mAuthRepository).onLogoff();
    }

}
