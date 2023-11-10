package dev.tabansi.palm.palmapp.data

object AppData {
    object Settings {
        var systemDefaultTheme: Boolean = true
        var darkTheme: Boolean? = null
        var defaultChatSettings: ChatSettings = ChatSettings()
        private val originalSettings = defaultChatSettings
    }

    val chats = mutableListOf<Chat>()

    var sampleChat = Chat().apply {
        // 0
        addMessage(text = "Hello")
        // 1
        addMessage(text = " Hello! How can I help you today? ")
        // 2
        addMessage(text = "What are you?")
        // 3
        addMessage(text = "I am a large language model, also known as a conversational AI or chatbot trained to be informative and comprehensive. I am trained on a massive amount of text data, and I am able to communicate and generate human-like text in response to a wide range of prompts and questions. For example, I can provide summaries of factual topics or create stories. ")
        // 4
        addMessage(text = "Tell me a story about a young raven.")
        // 5
        addMessage(text = "Once upon a time, there was a young raven named Rascal who lived in a forest. Rascal was a curious and adventurous raven, and he loved to explore the forest. One day, Rascal was exploring the forest when he came across a strange object. The object was a large, metal box, and Rascal had never seen anything like it before. Rascal was curious about the box, so he decided to investigate. Rascal flew up to the box and tried to open it, but the box was locked. Rascal was determined to open the box, so he started to peck at it with his beak. After a few minutes, Rascal finally managed to open the box. Inside the box, Rascal found a strange creature. The creature was small and green, and it had big, round eyes. Rascal had never seen anything like the creature before. The creature was just as curious about Rascal as Rascal was about it. The creature stared at Rascal with its big, round eyes, and Rascal stared back. After a few minutes, the creature spoke. \"Hello,\" said the creature. \"My name is Gizmo.\" Rascal was surprised that the creature could talk. \"Hello,\" said Rascal. \"My name is Rascal.\" Rascal and Gizmo became friends, and they spent many hours playing together in the forest. One day, Rascal and Gizmo were playing hide-and-seek when Gizmo hid in the box. Rascal couldn't find Gizmo, and he started to worry. Rascal searched the forest for Gizmo, but he couldn't find him anywhere. Rascal was about to give up when he heard a noise coming from the box. Rascal opened the box, and he was relieved to see that Gizmo was safe. \"I'm so glad you're okay,\" said Rascal. \"I was so worried about you.\" \"I'm okay,\" said Gizmo. \"I just got a little scared.\" Rascal and Gizmo continued to be friends, and they had many more adventures together. ")
        // 6
        addMessage(text = "Summarize \"The Raven\" by Edgar Allen Poe.")
        // 7
        addMessage(text = "The Raven is a narrative poem by Edgar Allan Poe. First published in January 1845, the poem is often noted for its musicality, stylized language, and supernatural atmosphere. It tells of a talking raven's mysterious visit to a distraught lover, tracing the man's slow fall into madness.\n" +
                "\n" +
                "The poem opens with the narrator lamenting the loss of his love, Lenore. He is sitting alone in his study when a raven suddenly arrives. The raven perches on a bust of Pallas, the Greek goddess of wisdom, and refuses to leave. The narrator tries to make conversation with the raven, but the bird only responds with the word \"Nevermore.\"\n" +
                "\n" +
                "The narrator becomes increasingly agitated by the raven's presence. He tries to ignore the bird, but it continues to repeat the word \"Nevermore.\" The narrator begins to think that the raven is a messenger of death, and he imagines that it is trying to tell him that Lenore is lost forever.\n" +
                "\n" +
                "As the night progresses, the narrator's madness deepens. He sees Lenore's face in the raven's eyes, and he hears her voice calling his name. The raven continues to repeat the word \"Nevermore,\" and the narrator becomes convinced that it is trying to drive him insane.\n" +
                "\n" +
                "In the morning, the narrator wakes up to find that the raven has disappeared. He is relieved to be rid of the bird, but he is also filled with sadness. He knows that Lenore is truly gone, and he will never see her again.\n" +
                "\n" +
                "The Raven is a classic example of Gothic literature. It is a dark and atmospheric poem that explores themes of death, madness, and the supernatural. The poem's use of symbolism and imagery is particularly effective in creating a sense of unease and suspense.")
    }
}
