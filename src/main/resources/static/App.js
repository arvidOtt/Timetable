'use strict'

var app = angular.module('roomManagement', ["ngRoute"]);

    app.config(function($routeProvider){
        $routeProvider.when("/", {
            templateUrl: "view/dashboard.html",
            name: "Dashboard",
            controller: "DashboardController"
        }).when("/room", {
            templateUrl: "view/room.html",
            name: "RoomView",
            controller: "RoomController"
        }).when("/docent", {
            templateUrl: "view/docent.html",
            name: "Docent",
            controller: "DocentController"
        }).when("/lecture", {
            templateUrl: "view/lecture.html",
            name: "Lecture",
            controller: "LectureController"
        }).when("/course", {
            templateUrl: "view/course.html",
            name: "Course",
            controller: "CourseController"
        }).when("/exam", {
            templateUrl: "view/exam.html",
            name: "Exam",
            controller: "ExamController"
        }).when("/cohort", {
            templateUrl: "view/cohort.html",
            name: "Kohorte",
            controller: "CohortController"
        }).when("/maniple", {
            templateUrl: "view/maniple.html",
            name: "Manipel",
            controller: "ManipleController"
        }).when("/century", {
            templateUrl: "view/century.html",
            name: "Zenturie",
            controller: "CenturyController"
        }).when("/seminar", {
            templateUrl: "view/seminar.html",
            name: "Seminar",
            controller: "SeminarController"
        }).otherwise({
        redirectTo: "/"})
    });

    app.config(['$locationProvider', function($locationProvider) {
        $locationProvider.hashPrefix('');
    }]);

    app.factory('routeNavigation', function($route, $location) {
          var routes = [];
          angular.forEach($route.routes, function (route, path) {
            if (route.name) {
              routes.push({
                path: path,
                name: route.name,
                controller: route.controller
              });
            }
          });
          return {
            routes: routes,
            activeRoute: function (route) {
              return route.path === $location.path();
            }
          };
    });
