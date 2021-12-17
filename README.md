# IO-I21-Delta-JSON

## First steps in repo:


### Basic info:
#### Branches description:

| branch name | description                                                                                                                                               |
| ----------- | --------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `main`      | branch is a production branch, it should contain only full-working releases                                                                               |
| `develop`   | branch is a develop branch, it is the newest, working application version                                                                                 |
| `feature`   | branches are kind of branches that are used to work on the new feature, when work is done and branch is merged to the `develop`, branch should be deleted |
| `fix`       | branches are used for fixes of already existing code, when the fix is done and branch is merged to the `develop`, branch should be deleted                |

#### More info
- **No one** should directly commit to the `main` branch, only pull-request from branch `develop` approved  
- **No one** should directly commit to the `develop` branch, only pull-request from other (`feature`/`fix`) branches approved  
- When a new issue is started, a new branch for this issue should be created  
- When work is done pull-request to the `develop` branch should be created  

### Every new branch must follow these rules:
- Branch name must match `feature/task_id-short_description` pattern (as fewest words as possible)
- New branch should match the following regular expression: `^(feature|fix)\/(([a-zA-Z0-9]+-?[a-zA-Z0-9]+-[a-zA-Z0-9]+[a-zA-Z_0-9]+))`
#### Example:
- `feature/CU-1xwcn70-branch_name`


### Every new commit must follow these rules:
- Commit name must match `task_id-short-description` pattern (as fewest words as possible)
- New commit should match the following regular expression: `^([a-zA-Z0-9]+-[a-zA-Z0-9]+-?[a-zA-Z0-9]+[a-zA-Z_0-9]+)`
#### Examples:
- `CU-1xwcn70-edit_some_code`
- `CU-1xwcn70-add_very_important_functionality`

### When detailed description needed, use following contruction:
```bash
git commit -m "Title" -m "" -m "Desciption line one" -m "Descirption line two"
```
It should create:
```txt
Title

Desciption line one
Desciption line two
```


---
<p align='center' >Created by <b>Delta Team</b></p>

---
</br>

[-> Product and Sprint Backlog <-](https://1drv.ms/x/s!BFaK2JgIzo5J0SG2HdcP4ypmys5n?e=BtySb2)
