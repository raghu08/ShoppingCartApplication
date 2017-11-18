
I have given more emphasis on how to architect an application rather than a
beautiful Userinterface(Which needs more time)

I have used following components for the application
1.Retrofit for accessing webservice(A http library)
2.Android Architecture components like Livedata and viewmodel  which were released recently by google

3.Used Viewmodel pattern recommended by google


Architecture Of ZooplusCart Application

There are three modules that are present in the application

UI(or View)
ViewModel
Repository

Ui & ViewModel:
A ViewModel provides the data for a specific UI component, such as a ProductListActivityFragment and handles the
communication with the business part of data handling, such as calling other components to load the
data . The ViewModel does not know about the View and is not affected by configuration changes such
as recreating an activity due to rotation.

ZooPlusRepository:
ViewModel could directly call the Webservice to fetch the data and assign it back to the user object.
Even though it works, the  app will be difficult to maintain as it grows. It gives too much responsibility
to the ViewModel class which goes against the principle ofseparation of concerns.Instead, our ViewModel
will delegate this work to a new ZooPlusRepository module.ZooPlusRepository modules are responsible for handling data operations. They provide a
clean API to the rest of the app.

This repository module can be extended to accommodate persisting data by following DAO pattern.