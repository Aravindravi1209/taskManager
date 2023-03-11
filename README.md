# Task Manager App

## JSON Entities

### Task
    {
	    "id": 31,
	    "title": "I am a task!",
	    "description": "This is the task description",
	    "deadline": "dd-mm-yyyy",
      "notes": [
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        },
        {
          "title": "random note title",
          "body": "I am the note body"
        }
      ],
      "completed": false
    }

## API Endpoints 

### `POST /tasks/add` 
Create a new task  

### `GET /tasks/all`
Get all tasks
Available filters - 
- `tasks/status?completed=true/false`

### `GET /tasks/{task_id}`
Get the details of a particular task including notes

### `PATCH /tasks/{task_id}/complete`
Mark a task completed.

### `DELETE /tasks/{task_id}/delete`
Delete a particular task

---- ADDITIONAL TASKS (BONUS) -----

### `GET  /tasks/{task_id}/notes`
Fetch all the notes under a particular task 

### `POST  /tasks/{task_id}/notes` 
Create a new note under the task with given task id 

### `DELETE /tasks/{task_id}/notes/{notes_id}`
Delete a note
