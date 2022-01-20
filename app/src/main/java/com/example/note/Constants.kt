package com.example.note

class Constants {

    // Константы DetailsFragment
    object DetailsView{
        const val INTENT_TEXT_TYPE : String  = "text/plain"
    }

    //Константы открытия ViewPagerActivity
    object Transaction{
        const val PASS_ACTION = "position"
        const val ADD_ACTION = "isAdding"
    }

    //Базовый URL для запросов
    object Url{
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}