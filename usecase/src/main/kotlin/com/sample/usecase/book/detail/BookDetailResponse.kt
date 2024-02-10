package com.sample.usecase.book.detail

import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(description = "書籍詳細レスポンス")
data class BookDetailResponse(
    @Schema(description = "書籍ID", example = "1")
    val id: String,
    @Schema(description = "書籍名", example = "ONE PIECE")
    val name: String,
    @Schema(description = "著者名", example = "尾田 栄一郎")
    val author: String,
    @Schema(description = "レンタル情報")
    val rentals: List<BookDetailRentalResponse>,
) {
    constructor(dto: BookDetailDto) : this(
        id = dto.id.toString(),
        name = dto.name!!,
        author = dto.author!!,
        rentals = dto.rentals?.map {
            BookDetailRentalResponse(
                userId = it.userId.toString(),
                rentedAt = it.rentedAt.toZonedDateTime(),
            )
        }.orEmpty()
    )
}

@Schema(description = "レンタル情報")
data class BookDetailRentalResponse(
    @Schema(description = "レンタルユーザID", example = "1")
    val userId: String,
    @Schema(description = "レンタル日付", example = "2023-01-01")
    val rentedAt: ZonedDateTime,
)
