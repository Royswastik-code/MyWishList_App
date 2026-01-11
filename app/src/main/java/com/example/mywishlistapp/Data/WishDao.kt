package com.example.mywishlistapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    //adds a wish to the wish table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)

    //loads all wishes from the wish table
    @Query("Select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    //update a wish in the wish table
    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    //delete a wish from the wish table
    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    //get a wish by id from the wish table
    @Query("Select * from `wish-table` where id=:id")
    abstract fun getAWishById(id:Long):Flow<Wish>
}
