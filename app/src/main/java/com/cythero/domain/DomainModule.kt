package com.cythero.domain

import com.google.gson.GsonBuilder
import com.cythero.data.utils.Urls
import com.cythero.presentation.util.event.EventSendableHolder
import com.cythero.util.DateFormat
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.*

class DomainModule : InjektModule {
    companion object {
        private const val USE_FAKES = true
    }
    
    override fun InjektRegistrar.registerInjectables() {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        addSingletonFactory {
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        
        addSingletonFactory {
            GsonBuilder()
                .setDateFormat(DateFormat.defaultRequestDateFormat().toPattern())
                .create()
        }

        addSingletonFactory {
            Retrofit.Builder()
                .baseUrl(Urls.API.baseUrlLocal)
                .addConverterFactory(GsonConverterFactory.create(Injekt.get()))
                .client(Injekt.get())
        }

        addSingletonFactory {
            Injekt.get<Retrofit.Builder>()
                .build()
        }

        when (USE_FAKES) {
            true -> {
                /*
                addSingletonFactory<ProjectRepository> { FakeProjectRepository }
                addSingletonFactory<ProjectTableRepository> { FakeProjectTableRepository }
                addSingletonFactory<TableTaskRepository> { FakeTableTaskRepository }
                addSingletonFactory<UserAuthorityRepository> { FakeUserAuthorityRepository }
                 */
            }
            false -> {
                /*
                addSingletonFactory<ProjectRepository> { RemoteProjectRepository }
                addSingletonFactory<ProjectTableRepository> { RemoteProjectTableRepository }
                addSingletonFactory<TableTaskRepository> { RemoteTableTaskRepository }
                addSingletonFactory<UserAuthorityRepository> { RemoteUserAuthorityRepository }
                 */
            }
        }

        /*
        addFactory { GetProject(get()) }
        addFactory { CreateProject(get()) }
        addFactory { RenameProject(get()) }
        addFactory { DeleteProject(get()) }

        addFactory { GetProjectTable(get()) }
        addFactory { CreateProjectTable(get()) }
        addFactory { RenameProjectTable(get()) }
        addFactory { SwapProjectTables(get()) }
        addFactory { DeleteProjectTable(get()) }

        addFactory { GetTableTask(get()) }
        addFactory { CreateTableTask(get()) }
        addFactory { SwapTableTasks(get()) }
        addFactory { MoveTableTask(get()) }
        addFactory { SwapTableTaskTable(get()) }
        addFactory { UpdateTableTaskDescription(get()) }

        addFactory { GetUserAuthorities(get()) }
        addFactory { CreateUserAuthority(get()) }
        addFactory { DeleteUserAuthority(get()) }
         */
    }
}
