package dev.tabansi.palm.palmapp.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.generativelanguage.v1beta3.DiscussServiceClient
import com.google.ai.generativelanguage.v1beta3.DiscussServiceSettings
import com.google.ai.generativelanguage.v1beta3.Example
import com.google.ai.generativelanguage.v1beta3.GenerateMessageRequest
import com.google.ai.generativelanguage.v1beta3.Message
import com.google.ai.generativelanguage.v1beta3.MessagePrompt
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider
import com.google.api.gax.rpc.FixedHeaderProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val apiKey = "AIzaSyBkEL7fPGxIV1BXXxBz1MllW4eJupjKKuQ"

/*
Generating Messages
Create a new file named MainViewModel.kt and create a basic ViewModel.
This is the file where you'll add all the functions to interact with the API.
 */
class SampleChatViewModel : ViewModel() {
    // Add the stateFlow
    private val _messages = MutableStateFlow<List<Message>>(value = listOf())
    val messages: StateFlow<List<Message>>
        get() = _messages

    // Add this variable (5.2)
    private var client: DiscussServiceClient

    // (5.2)
    init {
        // Initialize the Discuss Service Client
        client = initializeDiscussServiceClient(
            apiKey = apiKey
        )

        // Create the message prompt
        val prompt = createPrompt("How tall is the Eiffel Tower?")

        // Send the first request to kickstart the conversation
        val request = createMessageRequest(prompt)
        generateMessage(request)
    }

    /**
    1. Initialize the Discuss Service Client

    Initialize a DiscussServiceClient by passing your API Key as a header to the
    TransportChannelProvider to be used by DiscussServiceSettings:
     */
    // (This is a workaround because GAPIC java libraries don't yet support API key auth)
    private fun initializeDiscussServiceClient(apiKey: String): DiscussServiceClient {
        val transportChannelProvider = InstantiatingGrpcChannelProvider.newBuilder()
            .setHeaderProvider(FixedHeaderProvider.create(hashMapOf("x-goog-api-key" to apiKey)))
            .build()

        // Create DiscussServiceSettings
        val settings = DiscussServiceSettings.newBuilder()
            .setTransportChannelProvider(transportChannelProvider)
            .setCredentialsProvider(FixedCredentialsProvider.create(null))
            .build()

        // Initialize and return a DiscussServiceClient
        return DiscussServiceClient.create(settings)
    }

    /**
     * 2. Create a Message Prompt
     *
     * You need to provide a MessagePrompt to the API so that it can predict what is the next
     * message in the discussion.
     *
     * 2.1. (optional) Create some examples
     * Optionally, you can provide some examples of what the model should generate. This includes
     * both user input and the response that the model should emulate.
     */
    private fun createCaliforniaExample(): Example {
        val input = Message.newBuilder()
            .setContent("What is the capital of California?")
            .build()

        val response = Message.newBuilder()
            .setContent(
                "If the capital of California is what you seek, Sacramento is " +
                        "where you ought to peek."
            )
            .build()

        return Example.newBuilder()
            .setInput(input)
            .setOutput(response)
            .build()
    }

    /**
     * 2.2. Create the prompt
     * Pass the examples to the MessagePrompt.Builder along with the current message history and
     * optionally the example from the previous step.
     */
    private fun createPrompt(messageContent: String): MessagePrompt {
        val palmMessage = Message.newBuilder()
            .setAuthor("0")
            .setContent(messageContent)
            .build()

        // Add this line to update the UI
        _messages.update {
            it.toMutableList().apply {
                add(palmMessage)
            }
        }

        return MessagePrompt.newBuilder()
            .addMessages(palmMessage) // required
            .setContext("Respond to all questions with a rhyming poem.") // optional
            .addExamples(createCaliforniaExample()) // use addAllExamples() to add a list of examples
            .build()
    }

    /**
     * 3. Generate Messages
     *
     * 3.1 Create a GenerateMessageRequest
     * Create a GenerateMessageRequest by passing a model name and
     * prompt to the GenerateMessageRequest.Builder:
     */
    private fun createMessageRequest(prompt: MessagePrompt): GenerateMessageRequest {
        return GenerateMessageRequest.newBuilder()
            .setModel("models/chat-bison-001") // required, which model to use to generate the result
            .setPrompt(prompt) // required
            .setTemperature(0.5f) // optional, controls the randomness of the output
            .setCandidateCount(1) // optional, the number of messages to return
            .build()
    }

    /**
     * 3.2 Send the request
     * The API currently only provides blocking synchronous methods, so consider making this call
     * in a coroutine scope (eg. viewModelScope) to suspend its execution:
     */
    private fun generateMessage(request: GenerateMessageRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = client.generateMessage(request)
                val returnedMessage = response.candidatesList.last()
                // Display the returned message in the UI
                _messages.update {// (5.1)
                    // Add the response to the list
                    it.toMutableList().apply {
                        add(returnedMessage)
                    }
                }
            } catch (e: Exception) {
                // API returned an error
                // There was an error, let's add a new message with the details (5.1)
                _messages.update { messages ->
                    val mutableList = messages.toMutableList()
                    mutableList.apply {
                        add(
                            Message.newBuilder()
                                .setAuthor("API Error")
                                .setContent(e.message)
                                .build()
                        )
                    }
                }
            }
        }
    }

    /**
     * 4. Continue conversations
     *
     * In order to continue the conversation, you can call createPrompt() once again with
     * the new user input, and then generate the message again:
     */
    fun sendMessage(userInput: String) {
        val prompt = createPrompt(userInput)
        val request = createMessageRequest(prompt)
        generateMessage(request)
    }

    /**
     * 5. Put it all together
     *
     * 5.1. In the MainViewModel class, create a StateFlow of messages that will be exposed to the UI,
     * and update the generateMessage() function to emit updates to that flow:
     */
    // Add StateFlow
    // Update generateMessages() with error handling and updating state
    /**
     * 5.2 Create the DiscussServiceClient, a MessagePrompt and a GenerateMessageRequest to send the
     * first request in the MainViewModel's initializer block. Note that you'll need to pass your
     * own API key below:
     */
    //add client variable and init block
    /**
     * 5.3. Update the createPrompt() function to display new messages in the UI when they're sent:
     */
}

class SampleTextViewModel : ViewModel() {

}