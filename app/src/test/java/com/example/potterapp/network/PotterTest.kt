package com.example.potterapp.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.potterapp.rules.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class PotterTest {

@Rule
@JvmField
val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

@ExperimentalCoroutinesApi
@get:Rule
val coroutinesRule = CoroutineTestRule()

private lateinit var service: PotterService
private lateinit var server: MockWebServer

@Before
fun setUp() {
server = MockWebServer()
service = Retrofit.Builder()
.baseUrl(server.url(""))//We will use MockWebServers url
.addConverterFactory(GsonConverterFactory.create())
.build()
.create(PotterService::class.java)
}

private fun enqueueMockResponse(fileName: String) {
javaClass.classLoader?.let {
val inputStream = it.getResourceAsStream(fileName)
val source = inputStream.source().buffer()
val mockResponse = MockResponse()
mockResponse.setBody(source.readString(Charsets.UTF_8))
server.enqueue(mockResponse)
}
}

@After
fun tearDown() {
server.shutdown()
}

@Test
fun getCharacter_test() {
runBlocking {
// Prepare fake response
enqueueMockResponse("actors.json")
//Send Request to the MockServer
val responseBody = service.getActors()
//Request received by the mock server
val request = server.takeRequest()
assert(responseBody.isNotEmpty())
}
}
}