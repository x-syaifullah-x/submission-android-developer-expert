package com.android.developer.expert.presentation.detail

import androidx.lifecycle.Observer
import com.android.developer.expert.core.domain.model.DetailMovieModel
import com.android.developer.expert.core.domain.model.DetailTvModel
import com.android.developer.expert.core.domain.model.Resource
import com.android.developer.expert.core.domain.usecase.Interactor
import com.android.developer.expert.utils.RuleUnitTestWithMockito
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito

class DetailViewModelTest : RuleUnitTestWithMockito() {
    private lateinit var viewModel: DetailViewModel
    private val id = 100
    private val errorMessage = "Error"

    @Mock
    lateinit var interact: Interactor

    @Mock
    lateinit var mockObserverMovie: Observer<Resource<DetailMovieModel>>

    @Mock
    lateinit var mockObserverTv: Observer<Resource<DetailTvModel>>

    @Captor
    lateinit var captorMovie: ArgumentCaptor<Resource<DetailMovieModel>>

    @Captor
    lateinit var captorTv: ArgumentCaptor<Resource<DetailTvModel>>

    override fun before() {
        viewModel = DetailViewModel(interact)
    }

    @Test
    fun movieTestError() {
        Mockito.`when`(interact.getMovie(id))
            .thenReturn(flowOf(Resource.Error(Exception(errorMessage))))
        viewModel.refresh(id)

        viewModel.movie.observeForever(mockObserverMovie)
        Mockito.verify(interact).getMovie(id)
        Mockito.verify(mockObserverMovie).onChanged(captorMovie.capture())

        Assert.assertTrue(captorMovie.value is Resource.Error)
        val error = (captorMovie.value as Resource.Error).throwable
        Assert.assertEquals(errorMessage, error.message)
    }

    @Test
    fun movieTestEmpty() {
        Mockito.`when`(interact.getMovie(id)).thenReturn(flowOf(Resource.Empty))
        viewModel.refresh(id)

        viewModel.movie.observeForever(mockObserverMovie)
        Mockito.verify(interact).getMovie(id)
        Mockito.verify(mockObserverMovie).onChanged(captorMovie.capture())

        Assert.assertTrue(captorMovie.value is Resource.Empty)
    }


    @Test
    fun tvTestError() {
        Mockito.`when`(interact.getTv(id))
            .thenReturn(flowOf(Resource.Error(Exception(errorMessage))))
        viewModel.refresh(id)

        viewModel.tv.observeForever(mockObserverTv)
        Mockito.verify(interact).getTv(id)
        Mockito.verify(mockObserverTv).onChanged(captorTv.capture())

        Assert.assertTrue(captorTv.value is Resource.Error)
        val error = (captorTv.value as Resource.Error).throwable
        Assert.assertEquals(errorMessage, error.message)
    }

    @Test
    fun tvTestEmpty() {
        Mockito.`when`(interact.getTv(id)).thenReturn(flowOf(Resource.Empty))
        viewModel.refresh(id)

        viewModel.tv.observeForever(mockObserverTv)
        Mockito.verify(interact).getTv(id)
        Mockito.verify(mockObserverTv).onChanged(captorTv.capture())

        Assert.assertTrue(captorTv.value is Resource.Empty)
    }
}