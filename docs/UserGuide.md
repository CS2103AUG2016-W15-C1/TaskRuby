User Guide
==========

-   [Introduction](#introduction)
-   [Quick Start](#quick-start)
-   [Features](#features)
-   [FAQ](#faq)
-   [Command Summary](#command-summary)

<!-- @@author A0108515L --> 
INTRODUCTION
------------

Do you feel bombarded with 'things to do' continuously? Do you keep
forgetting stuff to do? Fear not, for you now have TaskRuby! A Getting
Things Done (GTD) Tool created by TeamRuby, to help YOU get things done!

Benefits of TaskRuby: Command Line Interface, One-shot Approach,
Internet-independent!

QUICK START
-----------

1.  Ensure you have Java version `1.8.0_60` or later installed in your computer.

    > Having any Java 8 version is not enough. TaskRuby will not work with earlier versions of Java 8.

2.  Download the latest `TaskRuby``.jar` from the [releases](../../../releases) tab.

3.  Copy the file to the folder you want to use as the home folder for your TaskRuby application.

4.  Double-click the file to start the application. The GUI (below) should appear in a few seconds.

    <img src="images/GUI_1.JPG"><br>

    Figure 1: GUI Display

5.  Type the command in the command box and press Enter to execute it. 
    e.g. typing `help` and pressing Enter will open the help window. 
    
    Some example commands you can try:

    >   `list` : lists all tasks.

    >   `add` `Buy vegetables` `\D today ` `\p 2` : adds a task named `Buy vegetables` to TaskRuby.

    >   `delete` `1` : deletes the 1st task from TaskRuby.

    >   `exit` : exits TaskRuby.

6.  Refer to the [Features](#features) section below for details of each command.

<!-- @@author A0130164W --> 
FEATURES
---------

> Command Format

>-   Words in UPPER_CASE are the parameters.
>-   Items in SQUARE_BRACKETS are optional.
>-   Items with … after them can have multiple instances.

#### Viewing help: `help` 

Displays all available commands in a separate window. 

Format: `help`

#### Adding a task: `add` 

Adds a task to TaskRuby.

Format:
`add` `TASK` `[\d START_DATE_TIME] [\D END_DATE_TIME] [\p PRIORITY_TAG]`

> Both date time can be entered in any format and are not compulsory: e.g. today 5pm, 1900 tomorrow, 25 nov, 5.30pm

> Priority Tag ranges from 1 to 3: **1**-High, **2**-Medium, **3**-Low

Examples:

-   `add` `Buy vegetables \D 10pm today`
-   `add` `CS2103T Tutorial \d tomorrow 1000 \D 1100 \p 1`

#### Deleting a task: `delete`

Deletes the specified task from TaskRuby.

Format: `delete` `TASK_ID`

> The ID refers to the number shown in the most recent listing and MUST be a positive integer: i.e. 1, 2, 3, ...

Examples:

-   `list`, `delete 2` : Deletes the second task in TaskRuby
-   `find CS2103`, `delete 1` : Deletes the 1st task in the results of the `find` command

#### Finding tasks: `find`

Finds tasks which contain any of the given keywords in the task name.

Format: `find` `KEYWORD [MORE_KEYWORDS]`

> Only task names are searched (case insensitive)

> Order of the keywords does not matter: e.g. `find` `CS2103T Tutorial` will match `Tutorial CS2103T`

> Only full words will be matched: e.g. `find` `CS2103T` will not match `CS2103` but `find CS2103` will match `CS2103` and `CS2103T`

> Tasks matching at least one keyword will be returned (i.e. **OR** search): e.g. **find** `CS2103T` will match `CS2103T Tutorial`

Examples:

-   `find` `CS2103T` : Returns `CS2103T Tutorial` but not `CS2103 Tutorial`
-   `find` `CS2103T` `CS2101` `CS3235` : Returns all tasks with keywords `CS2103T`, `CS2101`, or `CS3235`

<!-- @@author A0144017R --> 

#### Updating a task: `update`

Updates the status of the task.

Format: `update` `TASK_ID STATUS`

Examples:

-   `list`, `update 1 completed` : Updates status of 1st task to 'completed'
-   `find` `CS2103T`, `update` `2 completed` : Updates status of 2nd task in the results of ‘CS2103T’ to 'completed'

#### Undoing a task: `undo`

Undo the latest task added/deleted/updated. Irreversible.

Format: `undo`

> Undo ONLY the latest task added/deleted/updated: i.e. Unable to undo tasks added 2 'adds' ago

Examples:

-   `add` `Buy vegetables \D 10pm today`, `undo` : Undo latest add command, removes ‘Buy vegetables’ task
-   `find CS2103T`, `delete 1`, `undo`: Undo latest delete command, re-instates task 1 from the results of 'CS2103T'

#### Editing a task: `edit`

Edits a task component.

Format: `edit` `TASK_ID [\t NEW_TASK_NAME] [\d NEW_START_DATE_TIME] [\D NEW_END_DATE_TIME] [\p NEW_PRIORITY_TAG]`

Examples:

-   `list`, `edit 1 \t CS2103 Tutorial` : Changes 1st task's name to 'CS2103 Tutorial'
-   `edit` `2 \d tomorrow \p 1` : Changes 2nd task's start time to tomorrow and priority to HIGH

#### Listing all tasks: `list`

Shows a list of all tasks currently in TaskRuby.

Format: `list`

#### Hiding all tasks: `hide`

Hides all tasks currently in TaskRuby.

Format: `hide`

#### Clearing all entries: `clear`

Clears all entries from the task manager.

Format: `clear`

#### Exiting the program: `exit`

Exits the program.

Format: `exit`

#### Saving the data

Data is saved in the hard disk automatically after any command that
changes it. There is no need to save manually.

<!-- @@author A0118894N -->
 
FAQ
---

**Q**: Why does the application not start even after double-clicking the JAR file?

**A**: Ensure that you have a Java version 1.8.0_60 or later installed in your system.

**Q**: How do I check the current Java version installed on my PC?

**A**: Press the windows (or start menu) button, and type ‘About Java’, hit enter. There will be a pop-up (see Figure 2 below) 
indicating the current Java version on your PC.

<img src="images/AboutJava.png"><br>

Figure 2. About Java pop-up

**Q**: How do I transfer my data to another computer?

**A**: Install the application in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous TaskRuby folder.


COMMAND SUMMARY
---------------

  **Command**   | **Format**
  ------------- | -----------------------------------------------------------------------
  Add           | add TASK [\d START_DATE_TIME] [\D END_DATE_TIME] [\p PRIORITY_TAG]
  Clear         | clear
  Delete        | delete TASK_ID
  Edit          | edit TASK_ID [\t NEW_TASK_NAME] [\d NEW_START_DATE_TIME] [\D NEW_END_DATE_TIME] [\p NEW_PRIORITY_TAG]
  Exit          | exit
  Find          | find KEYWORD [MORE_KEYWORDS]
  Help          | help
  Hide          | hide
  List          | list
  Undo          | undo
  Update        | update TASK_ID STATUS
