package com.undabot.storeandflow.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
data class JsonDocument<T>(
  val included: List<Resource>? = null,
  val errors: List<Error>? = null
)

@JsonClass(generateAdapter = true)
data class Error(
  val code: String? = null,
  val title: String? = null,
  val detail: String? = null
)

abstract class Resource {
  abstract val responseCode: Int
}

@JsonClass(generateAdapter = true)
data class ResourceObject(
  override val responseCode: Int) : Resource()

//@JsonClass(generateAdapter = true)
//data class Errors(
//  val errors: List<Error>?
//)

class ApiResourceAdapter {
  // Use:
  // - https://github.com/square/moshi/blob/master/adapters/src/main/java/com/squareup/moshi/adapters/PolymorphicJsonAdapterFactory.java
  // - https://proandroiddev.com/moshi-polymorphic-adapter-is-d25deebbd7c5
  @FromJson
  fun from(resourceObject: ResourceObject): Resource = resourceObject

  @ToJson
  fun to(resource: Resource): ResourceObject = ResourceObject(resource.responseCode)
}