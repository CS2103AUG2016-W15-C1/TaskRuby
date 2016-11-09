Open the jar file and then do

See the help dialog box `box`

Then we add a normal task

`add Study for CS3235 exam \d now \D tomorrow 9 AM \p 1`

This will add a task called 'study for cs exam' starting now and ending tomorrow
with a high priority

Lets add a deadline task

`add submit cs1231 homework \D tomorrow 5 pm` which will add a task called
submit cs1231 due tomorrow 5 pm

Lets add a floating task

`add brush your teeth everyday \d morning 8 am \p 1`

Which will add a task called brush your teeth in the mornings with no deadline
(lets hope not)


Let's now add a simple todo task

`add learn the guitar`

This is more of a long term kind of task

To delete a command lets type `delete <task id>`

Let's add a wrong task `add something wrong`

Since this is wrong lets undo it :`undo`


To find a particular task lets do `find cs1231`

It'll show us the tasks associated with that word

To relist the tasks type `list`

To edit a task we can do 

`edit <task id> \t taskname \d dates \d dates \p prio`

So lets do `edit 1 \D tomorrow 10 am` 

or `edit 1 \t study for CS2333 exam` this changes the task completely

this will shift the first tasks deadline to 10 am

to mark a task as undo we have `update task_id done/not done/complete/incomplete`

so we have `update 1 done`

Since prof Damith is here lets hide the tasklist `hide`

Then to relist it lets type `list`


If you type an invalid command then nothing will happen
