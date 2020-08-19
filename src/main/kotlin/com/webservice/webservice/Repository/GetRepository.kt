package com.webservice.webservice.Repository

import com.webservice.webservice.Model.Data
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface GetRepository:CrudRepository<Data,Long> {


}

