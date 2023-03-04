package com.cajusoftware.fipe.data.repositories.models.sources

import android.content.res.Resources.NotFoundException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cajusoftware.fipe.BuildConfig.PAGE_SIZE
import com.cajusoftware.fipe.data.database.NoItemAddedException
import com.cajusoftware.fipe.data.database.dao.BrandDao
import com.cajusoftware.fipe.data.database.dtos.BrandModelDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

private const val STARTING_PAGING_INDEX = 0

class ModelsPagingSource(
    private val brandCode: String = "1",
    private val brandDao: BrandDao
) : PagingSource<Int, BrandModelDto>() {

    override fun getRefreshKey(state: PagingState<Int, BrandModelDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BrandModelDto> {
        val pageIndex = params.key ?: STARTING_PAGING_INDEX

        return withContext(Dispatchers.IO) {
            try {
                val result = brandDao.getBrandModelsForPaging(brandCode, pageIndex)

                if (pageIndex < 1 && result.isEmpty()) throw NoItemAddedException(brandCode)

                LoadResult.Page(
                    data = result,
                    prevKey = if (pageIndex == STARTING_PAGING_INDEX) null else pageIndex,
                    nextKey = if (result.isEmpty()) null else pageIndex + PAGE_SIZE
                )
            } catch (e: IOException) {
                LoadResult.Error(e)
            } catch (e: NotFoundException) {
                LoadResult.Error(e)
            }
        }
    }
}