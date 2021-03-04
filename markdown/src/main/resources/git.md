git 命令行记住密码  
```
git config --global credential.helper store
git config --system --unset credential.helper
```
---
git 设置代理   
`git config http.proxy socks5://127.0.0.1:7000`
---
```git
git config http.proxy socks5://127.0.0.1:7000
git config --global credential.helper store
git add .
git commit -m 'message'
git push
git pull
git rm -rf .idea
git rm dwl_not.iml
```

```git
echo "# empty" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/alibaba/easyexcel.git
git push -u origin main

git pull origin master

git config --global user.email wenlong.ding@zkj.com
git config --global user.name wenlong.ding
git config --global --list
git config --list
git config http.proxy socks5://127.0.0.1:7000

git branch --set-upstream-to=origin/dev_0.0.2 dev_0.0.2
```

