package com.example.note

class Constants {

    /**
     * Константы DetailsFragment
     */
    object DetailsView{
        // Константа для интента расшеривания заметки
        const val INTENT_TEXT_TYPE : String  = "text/plain"
    }

    /**
     * Константы открытия ViewPagerActivity
     */
    object Transaction{
        const val PASS_ACTION = "position"
        const val ADD_ACTION = "isAdding"
    }

    /**
     * Константы для работы с сетью
     */
    object Api{
        //Базовый URL для запросов
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}