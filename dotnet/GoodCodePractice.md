# 良好代码实践
编写可维护高质量的代码有利于公司后期项目的维护和沟通，但编写高质量代码很难制定统一的标准，也难以给出具体可度量的指标，本文结合大家日常代码开发中最常见的问题和公司项目情况，提出一些业内推荐的良好实践建议，希望帮助大家编写更优秀的代码

## 前言
本文主要从代码命名，方法编写和重构的角度，结合具体的实例来给予说明，很多建议来自网上和已出版软件工程书籍，具体来源将在文末参考给出

## 命名
命名要准确完全表达所代表的事务,自解释和恰如其份,用最短的长度表达最大的信息量

### 变量命名

#### 示例
|变量用途           |          good                       |          avoid         | 
------------------ | ------------------------------------| -----------------------| 
|收入金额/总额      |      revenueAmount/revenueTotal     |         amount, number |
|当前日期           |      currentDate, todaysDate        |         d, date, cd    |
|每页行数           |      pageSize, linesPerPage         |         size, lines    |
|缓存分钟数         |      cachingInMinutes               |         cm,time        |     

#### 表示计算结果
有些常用的表示计算结果的变量如总额，平均值，最大值等，建议结合问题域名词再加上相应的限定词,如revenueTotal, revenueAverage, revenueMax等

#### 变量命名使用对仗词
有些场景可能需要用一些对仗词作为变量前后缀，使用符合英语习惯的对仗词可提高可读性，下面是一些常用的对仗词，
begin/end, 
start/finish,
first/last,
locked/unlocked,
min/max,
next/previous,
old/new,
opened/closed,
visible/invisable,
source/destination,
up/down,
request/reponse, 
active/inactive,
enable/disable

#### 循环变量
```C#
foreach(var order in Orders) 
{
    
}
foreach(var invoice in Invoices)
{
}

foreach(var wo in workOrders)
{
}
```
#### bool变量
 一些典型的bool变量名done, error, found, succes or ok， completed, 不推荐在bool变量前加is 如isdone，iserror导致可读性还略差，人们一般习惯正向思维，尽量使用肯定的bool布量名, 反向导致较难阅读理解，如`if not notdone`

### 方法命名
方法命名一般建议动词或动名词,表示在对象上执行某一个动作

#### 常见示例
|动作前缀           |          意义                       |          例子          | 
------------------ | ------------------------------------| -----------------------| 
|create      |       新建                               |         CreatReactiveWorkOrder |
|new           |      新建                               |        NewWorkOrder    |
|from           |       由某物信息来创建         |         CreateWorkdOrderFromCall    |
|to         |      转换               |         ToString        |   
|update         |      更新即有事物               |         ToString        |   
|load         |      从某个存储区加载如内存，文件               |         LoadConfig        |   
|fetch         |      从远程获取               |         FetchWorkOrder        |   
|delete/remove         |      删除                |         DeleteOrder/removeOrder        |   
|save         |     存在update,不存在create               |         SaveWorkOrder        |   
|commit         |       一些操作后提交修改               |         CommitChange        |   
|apply         |      申请或应该某个规则               |         ApplyStyle        |   
|validate         |      验证               |         ValidateWorkOrder        |   
|ensure         |     检查不变条件               |         EnsureWorkOrderExist        |   

#### 返回bool值的方法命名示例
|前缀           |          意义                       |          例子         | 
------------------ | ------------------------------------| -----------------------| 
|is     |      是否所期望状态     |         IsCompleted  |
|can           |      能否执行所期望动作        |        CanRemove    |
|should           |      执行某个方法后期望达到特定状态         |       shouldCompleted    |
|has         |      是否持有所期望的数据或属性               |         hasValue       |     
|need         |      是否需要执行某个命令               |         needAccepted        |     

#### 按需执行
|前后缀           |          意义                       |          例子         | 
------------------ | ------------------------------------| -----------------------| 
|ifNeed     |     满足条件才执行     |         DrawIfNeed  |
|try           |      尝试执行        |        TryParseInt    |
|should           |      执行某个方法后期望达到特定状态         |       shouldCompleted    |
|orDefault         |      失败返回默认值               |         GetOrDefault       |     
|need         |      是否需要执行某个命令               |         needAccepted        |     

### 类命名
类命名应该遵守现有项目每一层约定的命名规范，如表现层 xxxController和xxxViewModel, 业务逻辑层xxxManager或xxxService。业务逻辑层命名尽量使用问题域相关，表达问题域概念和交互。 其它请参见[公司代码规范](http://52.80.129.21:8090/display/TD/SMS+ONE+Coding+Standards) 和
[微软设计规范](https://docs.microsoft.com/en-us/dotnet/standard/design-guidelines/)

## 可维护代码

###  方法内实现应该保持在一致的抽象层次

```C#
//bad 
public void PrintTable()
{
    PrintBanner();

    Console.Writeln("some header info");
    Console.Writeln("some body info");
}
```

```C#
//good 
public void PrintTable()
{
    PrintBanner();
    PrintHeader();
    PrintBody();
}
```

### 避免编写太长的方法，长的方法难以阅读和理解，一个经验值一个方法内的代码不应该超过一电脑屏幕，长的方法应该重新设计和重构
```C#
//bad 
//以是创建ReactiveWo部分代码
 public virtual async Task<ReactiveWoEntity> Create(ReactiveWoEntity entity, bool checkNTE = true)
        {
            Util.CheckRequired(entity, "Reactive wo entity");
            Util.CheckRequired(entity.LocationId, "Location id");
            Util.CheckRequired(entity.ServiceCodeId, "Service code id");
            ReactiveWoEntity reactiveWo;

            IReactiveWoCheckManager reactiveWoCheckManager = ServiceFactory.Get<IReactiveWoCheckManager>();
            ICurrentUser currentUser = ServiceFactory.Get<ICurrentUser>();
            IReactiveWoRepository repository = ServiceFactory.Get<IReactiveWoRepository>();
            IUserManager userManager = ServiceFactory.Get<IUserManager>();

            var isAutoSchedulePriorityId = false;
            var isAutoScheduleClient = false;
            var isAutoScheduleAbFeature = currentUser.AbFeatures.Contains((int)AbFeatures.AutoSchedule);
            var isAutoScheduleRegion = false;
            var IsWphandIHClientCAH = true;
            bool isAutoSchedulPendSchedule = false;
            using (var transaction = Transaction.BeginWrite())
            {             
                var woTagIds = GetWoTagIds(_commonQuestionAnswers, _serviceCodeQuestionAnswers);
                // it is no use code, because there is no wo id before created wo;
                //AddWOTag(entity.Id, woTagIds);
                var locationRepository = ServiceFactory.Get<ILocationRepository>();
                var location = await locationRepository.Get(entity.LocationId);
                Util.CheckEntity(location, entity.LocationId);
                entity.Location = location;
                entity.IsDispatchApproval = true;
                var serviceCodeManager = ServiceFactory.Get<IServiceCodeCommonManager>();
                var serviceCode = await serviceCodeManager.GetServiceCode(entity.ServiceCodeId);
                Util.CheckEntity(serviceCode, entity.ServiceCodeId);

                var locationBusinessUnitRepository = ServiceFactory.Get<ILocationBusinessUnitRepository>();
                var locationBu = await locationBusinessUnitRepository.Get(new LocationBusinessUnitQueryModel
                {
                    LocationId = entity.LocationId,
                    ServiceTypeId = serviceCode.ServiceTypeId
                });
                if (_isRefund == false)
                {
                    if (!locationBu.Items.Any())
                    {
                        throw new VerifyException("Location " + location.SMSNumber + " service type " +
                                                  serviceCode.ServiceType.Name + " not actived.");
                    }
                    var locationManager = ServiceFactory.Get<ILocationBasicManager>();
                    var activedServiceCodeList = await locationManager.GetActiveServiceCodeList(entity.LocationId, null);
                    if (activedServiceCodeList.Select(p => p.Id).Count(p => p == entity.ServiceCodeId) <= 0)
                    {
                        throw new VerifyException("Service code " + serviceCode.Name + " is inactive.");
                    }
                }
                if (location.Status == 0)
                {
                    throw new VerifyException("Location status is inactive, can not create WO. Please activate location in location info.");
                }
                isAutoSchedulePriorityId = entity.PriorityId != (short)PredefinePriority.Emergency && entity.PriorityId != (short)PredefinePriority.Critical;
                isAutoScheduleClient = location.ClientId == ((short)PredefinedClient.WPH).ToString() || location.ClientId == ((short)PredefinedClient.IH).ToString();
                //set high risk flag
                var locationAttributeRepository = ServiceFactory.Get<ILocationAttributeRepository>();
                var locationAttribute = (await locationAttributeRepository.Get(new LocationAttributeQueryModel
                {
                    LocationId = location.Id
                })).Items.FirstOrDefault();
                if (locationAttribute != null)
                {
                    entity.IsHighRisk = locationAttribute.IsHighRisk.GetValueOrDefault();
                }
             //省略大量代码
            return reactiveWo;
        }
```
```C#
//good 
//重新设计后问部分代码
public virtual async Task SaveWoAsync()
{
    InitWoNum();
    using (var tran = new TransactionScope())
    {
        await _serviceContext.ReactiveWoRepository.CreateReactiveWoAsync(Context.ReativeWo);
        await SetAndUpdateWoNumAsync();
        await CreateAssignLogAsync();
        await CreateNoteAsync();
        await CreateWoLinkAndWoContactAsync();
        await CreateWoAttributeAsync();
        await SaveAnswerQuestionsAsync();

        tran.Complete();
    }
}
```
### 消除重复代码，DRY原则(Don't Repeat Yourself)
####重构重复行为或代码到私有方法,增加说明性的函数，代码也更清晰
```C#
//bad
public void ExportExcel() 
{
    var workbook = new Workbook();
    var worksheet = workbook.Worksheets[0];

    worksheet.Cells[0, 0].PutValue("WO #");
    worksheet.Cells[0, 1].PutValue("CallTime");
    worksheet.Cells[0, 2].PutValue("ServiceCategory");
    worksheet.Cells[0, 3].PutValue("ServiceType");
    worksheet.Cells[0, 4].PutValue("ProblemCode");
    worksheet.Cells[0, 5].PutValue("Status");
    worksheet.Cells[0, 6].PutValue("Priority");
    worksheet.Cells[0, 7].PutValue("VendorName");
    worksheet.Cells[0, 8].PutValue("Store#");
    //省略其它代码
}
```

```C#
//good 
public void ExportExcel() 
{
    var workbook = new Workbook();
    var worksheet = workbook.Worksheets[0];
    
    SetHeader(worksheet, "WO #", "CallTime", "ServiceCategory");
}

private void SetHeader(worksheet, params string[] headers)
{
    //省略实现
}
```
#### 使用Action或Function消除简单小逻辑
```C#
//bad 
//字符串转16进制
private static int ConvertToHex(char ch)
{
    int val = 0;
    if (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f')
    {
        do
        {
            val = val * 16 + ch;
            if (ch >= '0' && ch <= '9')
            {
                val -= '0';
            }
            else if (ch >= 'A' && ch <= 'F')
            {
                val += 10 - 'A';
            }
            else if (ch >= 'a' && ch <= 'f')
            {
                val += 10 - 'a';
            }
            ch = GetNextChar();
        } while (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f');
    }

    return val;
}
```
```C#
//good 
private static int ConvertToHex(char ch)
{
    int val = 0;
    if (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f')
    {
        do
        {
            val = val * 16 + ch;
            if (ch >= '0' && ch <= '9')
            {
                val -= '0';
            }
            else if (ch >= 'A' && ch <= 'F')
            {
                val += 10 - 'A';
            }
            else if (ch >= 'a' && ch <= 'f')
            {
                val += 10 - 'a';
            }
            ch = GetNextChar();
        } while (ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f');
    }

    return val;
}
```
