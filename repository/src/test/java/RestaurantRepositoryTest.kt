import com.fanilo.domain.repository.IRestaurantDataSource
import com.fanilo.entity.Answer
import com.fanilo.entity.Coordinate
import com.fanilo.entity.FailureReason
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.restaurant.Category
import com.fanilo.entity.restaurant.Restaurant
import com.fanilo.repository.RestaurantRepository
import com.fanilo.repository.service.ICacheRestaurantService
import com.fanilo.repository.service.IRemoteFoursquareService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RestaurantRepositoryTest {

    private lateinit var repository: IRestaurantDataSource
    private val remoteFoursquareService: IRemoteFoursquareService = mock()
    private val cacheService: ICacheRestaurantService = mock()

    @Before
    fun setUp() {
        repository = RestaurantRepository(remoteFoursquareService, cacheService)
    }

    @Test
    fun fetchNearRestaurant_Success() = runBlocking {
        whenever(remoteFoursquareService.fetchNearRestaurant("48.890798078907, 58.89798798")).thenReturn(
            Answer.Success(listOf(mockRestaurant))
        )

        repository.fetchNearRestaurant("48.890798078907, 58.89798798")
        verify(remoteFoursquareService).fetchNearRestaurant("48.890798078907, 58.89798798")
        verify(cacheService).updateRestaurantCache(listOf(mockRestaurant))
    }

    @Test
    fun fetchNearRestaurant_Failure() = runBlocking {
        whenever(remoteFoursquareService.fetchNearRestaurant("48.890798078907, 58.89798798")).thenReturn(
            Answer.Failure(Throwable(), "API ERROR", FailureReason.NETWORK)
        )

        repository.fetchNearRestaurant("48.890798078907, 58.89798798")
        verify(remoteFoursquareService).fetchNearRestaurant("48.890798078907, 58.89798798")
        verify(cacheService, never()).updateRestaurantCache(listOf(mockRestaurant))
    }

    @Test
    fun getCachedRestaurant() = runBlocking {
        whenever(cacheService.getAllRestaurant()).thenReturn(listOf(mockRestaurant))
        repository.getCachedRestaurant()
        verify(cacheService).getAllRestaurant()

        return@runBlocking
    }

    private companion object {
        val mockRestaurant = Restaurant(
            id = "id",
            location = Coordinate(LatitudeLongitude(48.890798078907, 58.89798798), "address", "Cool City"),
            category = listOf(Category("Fancy Restaurant")),
            name = "Very good restaurant"
        )
    }
}