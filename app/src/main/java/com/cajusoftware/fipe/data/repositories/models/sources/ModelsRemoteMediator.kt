//package com.cajusoftware.fipe.data.repositories.models.sources
//
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import com.cajusoftware.fipe.data.database.dao.BrandDao
//import com.cajusoftware.fipe.data.domain.BrandsModel
//import java.io.IOException
//
//@OptIn(ExperimentalPagingApi::class)
//class ModelsRemoteMediator(private val brandDao: BrandDao) : RemoteMediator<Int, BrandsModel>() {
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, BrandsModel>
//    ): MediatorResult {
//
//        return try {
//
//            val loadKey = when (loadType) {
//                LoadType.REFRESH -> null
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                        ?: return MediatorResult.Success(endOfPaginationReached = true)
//                    lastItem.code
//                }
//            }
//
//
//            val result = loadKey?.let { brandDao.getBrandModelsPaging(it) }
//
//
//
//            return MediatorResult.Success(
//                endOfPaginationReached = result?
//            )
//        } catch (e: IOException) {
//            MediatorResult.Error(e)
//        }
//    }
//}