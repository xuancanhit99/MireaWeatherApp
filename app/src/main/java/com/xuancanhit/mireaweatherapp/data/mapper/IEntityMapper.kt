package com.xuancanhit.mireaweatherapp.data.mapper

interface IEntityMapper<Entity, Model> {

    fun mapFromEntity(entity: Entity) : Model

    fun entityFromModel(model: Model) : Entity
}