package com.ahmedadel.kotlinapp.screens.login


import com.ahmedadel.kotlinapp.domain.Model
import io.reactivex.properties.BooleanProperty
import io.reactivex.properties.Property

/**
 * the parent class [Model] is a kotlin version from the Model.java
 * in the link :
 * https://gist.github.com/Ahmed-Adel-Ismail/c0ec1ed6c8d37c931b3bf42b22430246
 */
class LoginViewModel : Model() {

    // Property is an Object that holds one item,
    // and can be converted to RxJava Observable
    // through Property.asObservable() method
    val userName = Property<String>()
    val password = Property<String>()
    val progress = BooleanProperty()
    val errorMessage = Property<String>()
    val userIdResponse = Property<Long>()

    override fun clear() {
        userName.clear()
        password.clear()
        progress.clear()
        errorMessage.clear()
        userIdResponse.clear()
    }
}
