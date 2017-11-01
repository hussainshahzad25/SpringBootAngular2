import { Http, Response, Headers, RequestOptions } from "@angular/http";
import "rxjs/Rx";
import { Task } from "./task.model";


import { HttpClient } from '@angular/common/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { Observable } from 'rxjs';
import { EventEmitter, Injectable } from "@angular/core";

@Injectable()
export class TaskService {

    onTaskAdded = new EventEmitter<Task>();

    // private taskList : string = "/api/tasks";
    // private savekLink : string = "/api/tasks/save";


    private taskList: string = "http://localhost:2030/api/tasks/";
    private savekLink: string = "http://localhost:2030/api/tasks/save";
    //private deleteLink: string = "http://localhost:2030/api/tasks";
    private deleteLink: string = "http://localhost:2030/api/tasks/deleteUsingBody";

    private getLink: string = "http://localhost:2030/api/tasks/";


    constructor(private http: Http) { }

    getTasks() {
        return this.http.get(this.taskList)
            .map(
            (response: Response) => {
                return response.json();
            }
            );
    }

    addTask(task: Task) {
        return this.http.post(this.savekLink, task)
            .map(
            (response: Response) => {
                console.log("Added Task :: " + response.json());
                return response.json();
            }
            );
    }



    saveTask(task: Task, checked: boolean) {
        // we are updating the task to what the value of checked is
        task.completed = checked;
        return this.http.post(this.savekLink, task)
            .map(
            (response: Response) => {
                console.log("Saved Task :: " + response.json());
                return response.json();
            }
            );
    }


    deleteTask(task: Task): Observable<any> {
        let body = JSON.stringify(task);
        console.log(body);
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });

        return this
            .http
            .post(this.deleteLink, body, options)
            .map((res: Response) => {
                alert("Dleteded");
                console.log("Deleted Task :: " + res.json());
            });
    }



}