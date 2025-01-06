```
Wuye_App/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   ├── src/
│   │   ├── androidTest/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/
│   │   │   │   └── com/example/wuye_app/
│   │   │   │       ├── MainActivity.java         # 主 Activity，负责底部导航栏的切换和 Fragment 管理
│   │   │   │       ├── modules/
│   │   │   │       │   ├── home/                 # 首页模块
│   │   │   │       │   │   ├── HomeActivity.java    # 首页 Activity，负责展示首页 UI 和处理用户交互
│   │   │   │       │   │   ├── HomeViewModel.java   # 首页 ViewModel，负责处理首页数据获取和业务逻辑
│   │   │   │       │   │   ├── adapters/          # 首页相关的适配器
│   │   │   │       │   │   │   ├── QuickActionsAdapter.java # 快捷操作列表适配器，用于展示功能入口图标
│   │   │   │       │   │   │   │   # 功能：
│   │   │   │       │   │   │   │   #   - 将快捷操作数据（图标，文字）绑定到 RecyclerView 的 Item 视图上。
│   │   │   │       │   │   │   │   #   - 处理 Item 的点击事件，根据点击的图标跳转到相应的功能页面。
│   │   │   │       │   │   │   ├── LifeServicesAdapter.java # 生活服务列表适配器，用于展示生活服务入口
│   │   │   │       │   │   │   │   # 功能：
│   │   │   │       │   │   │   │   #   - 将生活服务数据（图片、文字描述）绑定到 RecyclerView 的 Item 视图上。
│   │   │   │       │   │   │   │   #   - 处理 Item 的点击事件，跳转到相应的服务详情页面或功能。
│   │   │   │       │   │   ├── fragments/         # 首页相关的 Fragment (如果需要)
│   │   │   │       │   │   ├── **activity_home.xml**    # 首页的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部 (LinearLayout/ConstraintLayout):
│   │   │   │       │   │   │   #       - `ImageView`:  房屋图标，可能作为可点击的区域，用于切换物业等操作。
│   │   │   │       │   │   │   #       - `LinearLayout` (vertical):
│   │   │   │       │   │   │   #           - `TextView`: 显示用户所属的物业信息（小区名称），例如 "崔氏科技"。
│   │   │   │       │   │   │   #           - `TextView`: 显示楼栋单元信息，例如 "1区-1栋-1单元-0102"。
│   │   │   │       │   │   │   #       - `TextView`: 显示在线状态，例如 "在线"。
│   │   │   │       │   │   │   #   - 中部 (ScrollView to allow scrolling):
│   │   │   │       │   │   │   #       - 快捷操作区 (RecyclerView):
│   │   │   │       │   │   │   #           - `RecyclerView`，`layoutManager` 设置为 `GridLayoutManager` 或 `LinearLayoutManager` (horizontal)。
│   │   │   │       │   │   │   #           - 使用 `QuickActionsAdapter` 加载数据。
│   │   │   │       │   │   │   #           - 每个 Item (`item_quick_action.xml`) 包含一个 `ImageView` (功能图标) 和一个 `TextView` (功能名称)，例如 "用户通"、"监控" 等。
│   │   │   │       │   │   │   #       - 通知区域 (CardView 或 LinearLayout):
│   │   │   │       │   │   │   #           - `ImageView`:  通知图标 (例如小喇叭)。
│   │   │   │       │   │   │   #           - `TextView`:  "暂无通知" 或最新的社区通知标题。
│   │   │   │       │   │   │   #           - `ImageView`:  右箭头，点击跳转到通知列表页。
│   │   │   │       │   │   │   #           - (Optional) `ImageView`:  图片，当没有通知时显示的插画。
│   │   │   │       │   │   │   #           - (Optional) `TextView`:  当没有通知时显示的提示语，例如 "你暂时没有收到任何消息哦~"。
│   │   │   │       │   │   │   #   - 底部 (LinearLayout):
│   │   │   │       │   │   │   #       - `TextView`:  "生活服务" 标题。
│   │   │   │       │   │   │   #       - 生活服务区 (RecyclerView):
│   │   │   │       │   │   │   #           - `RecyclerView`，`layoutManager` 设置为 `GridLayoutManager`。
│   │   │   │       │   │   │   #           - 使用 `LifeServicesAdapter` 加载数据。
│   │   │   │       │   │   │   #           - 每个 Item (`item_life_service.xml`) 包含一个 `ImageView` (服务图片) 和一个 `TextView` (服务描述)，例如 "家政服务"、"快递服务" 等。
│   │   │   │       │   │   │   #       - `TextView`:  "没有更多内容" 或类似的提示语。
│   │   │   │       │   ├── profile/              # 我的 (个人资料) 模块
│   │   │   │       │   │   ├── ProfileActivity.java # 个人资料 Activity，负责展示个人信息和处理相关操作
│   │   │   │       │   │   ├── ProfileViewModel.java# 个人资料 ViewModel，负责处理个人资料的数据获取和业务逻辑
│   │   │   │       │   │   ├── **activity_profile.xml** # 个人资料页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部 (ConstraintLayout/LinearLayout):
│   │   │   │       │   │   │   #       - `ImageView`: 用户头像（圆形），可以使用 `CircleImageView` 等第三方库实现。
│   │   │   │       │   │   │   #       - `TextView`: 用户手机号码。
│   │   │   │       │   │   │   #       - `TextView`: 用户所属物业信息（小区名称、楼栋单元）。
│   │   │   │       │   │   │   #   - 中部 (RecyclerView 或 LinearLayout):
│   │   │   │       │   │   │   #       - `LinearLayout` (vertical) 用于排列功能入口。
│   │   │   │       │   │   │   #       - 每个 Item (LinearLayout):
│   │   │   │       │   │   │   #           - `ImageView`: 功能图标。
│   │   │   │       │   │   │   #           - `TextView`: 功能名称，例如 "切换业主"、"人脸录制"、"设置"。
│   │   │   │       │   │   │   #           - `ImageView`: 右箭头。
│   │   │   │       │   ├── door/                 # 开门模块
│   │   │   │       │   │   ├── DoorControlActivity.java # 开门控制 Activity，负责展示开门方式和处理开门请求
│   │   │   │       │   │   ├── **activity_door_control.xml** # 开门控制页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 主要内容区域 (LinearLayout/ConstraintLayout):
│   │   │   │       │   │   │   #       - `Button`: "点击开门" 按钮。
│   │   │   │       │   │   │   #       - `LinearLayout`: "扫码开门" 入口。
│   │   │   │       │   │   │   #           - `ImageView`: 扫码图标。
│   │   │   │       │   │   │   #           - `TextView`: "扫码开门" 文字。
│   │   │   │       │   │   │   #       - 可能包含其他开门方式的入口 (例如蓝牙开门)。
│   │   │   │       │   ├── intercom/             # 用户通 (可视对讲) 模块
│   │   │   │       │   │   ├── IntercomActivity.java  # 可视对讲 Activity，负责处理对讲逻辑和界面显示
│   │   │   │       │   │   ├── **activity_intercom.xml**  # 可视对讲页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 主要区域 (FrameLayout 或 TextureView): 用于显示对讲视频画面。
│   │   │   │       │   │   │   #   - 底部或侧边操作栏 (LinearLayout):
│   │   │   │       │   │   │   #       - `ImageButton`: "挂断" 按钮 (可能使用矢量图标)。
│   │   │   │       │   │   │   #       - `ImageButton`: "静音" 按钮 (可能使用矢量图标，并有选中/未选中状态)。
│   │   │   │       │   │   │   #       - `ImageButton`: "通话" 按钮 (如果需要双向语音)。
│   │   │   │       │   │   │   #       - `TextView`:  显示对讲状态或提示信息。
│   │   │   │       │   ├── surveillance/         # 监控模块
│   │   │   │       │   │   ├── SurveillanceActivity.java # 监控 Activity，负责展示监控视频流
│   │   │   │       │   │   ├── **activity_surveillance.xml** # 监控页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 主要区域 (FrameLayout 或 TextureView): 用于显示监控摄像头实时画面。
│   │   │   │       │   │   │   #   - 顶部工具栏 (LinearLayout/RelativeLayout):
│   │   │   │       │   │   │   #       - `ImageButton`: 返回按钮。
│   │   │   │       │   │   │   #       - `TextView`: 页面标题 "监控"。
│   │   │   │       │   │   │   #   - 底部操作栏 (LinearLayout):
│   │   │   │       │   │   │   #       - `ImageButton`: 摄像头切换按钮 (如果有多个摄像头)。
│   │   │   │       │   │   │   #       - `ImageButton`: 全屏/取消全屏按钮。
│   │   │   │       │   │   │   #       - `ImageButton`: 拍照按钮。
│   │   │   │       │   │   │   #       - `ImageButton`: 录像按钮 (可能有开始/停止状态)。
│   │   │   │       │   ├── visitor/              # 邀请访客模块
│   │   │   │       │   │   ├── InviteVisitorActivity.java # 邀请访客 Activity，负责处理访客邀请流程
│   │   │   │       │   │   ├── **activity_invite_visitor.xml** # 邀请访客页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 表单区域 (LinearLayout/ScrollView):
│   │   │   │       │   │   │   #       - `EditText`: 访客姓名输入框。
│   │   │   │       │   │   │   #       - `EditText`: 访客电话号码输入框。
│   │   │   │       │   │   │   #       - `TextView`: "到访时间" 标签。
│   │   │   │       │   │   │   #       - `EditText`: 到访时间选择框 (可点击弹出日期/时间选择器)。
│   │   │   │       │   │   │   #       - `TextView`: "备注" 标签 (可选)。
│   │   │   │       │   │   │   #       - `EditText`: 备注输入框 (可选，多行)。
│   │   │   │       │   │   │   #   - 操作按钮区域 (LinearLayout):
│   │   │   │       │   │   │   #       - `Button`: "生成邀请码" 按钮。
│   │   │   │       │   │   │   #   - 显示区域 (LinearLayout/CardView):
│   │   │   │       │   │   │   #       - `TextView`:  显示生成的邀请码。
│   │   │   │       │   │   │   #       - `ImageView`: 显示生成的二维码 (可以使用第三方库生成)。
│   │   │   │       │   │   │   #       - `TextView`:  邀请码/二维码的说明文字。
│   │   │   │       │   ├── elevator/             # 呼叫电梯模块
│   │   │   │       │   │   ├── CallElevatorActivity.java # 呼叫电梯 Activity，负责处理电梯呼叫请求
│   │   │   │       │   │   ├── **activity_call_elevator.xml** # 呼叫电梯页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 楼层选择区域 (NumberPicker, RecyclerView 或 LinearLayout):
│   │   │   │       │   │   │   #       -  可以使用 `NumberPicker` 组件让用户选择楼层。
│   │   │   │       │   │   │   #       -  或者使用 `RecyclerView` 展示楼层选项。
│   │   │   │       │   │   │   #       -  或者使用自定义的数字键盘布局。
│   │   │   │       │   │   │   #   - 操作按钮区域 (LinearLayout):
│   │   │   │       │   │   │   #       - `Button`: "呼叫" 按钮。
│   │   │   │       │   │   │   #   - (Optional) 显示电梯状态的区域 (TextView): 例如 "电梯正在上行/下行"。
│   │   │   │       │   ├── scan/                 # 扫码开门模块
│   │   │   │       │   │   ├── ScanToOpenActivity.java # 扫码开门 Activity，负责处理扫码逻辑和开门请求
│   │   │   │       │   │   ├── **activity_scan_to_open.xml** # 扫码开门页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 预览区域 (FrameLayout): 用于显示相机预览。可以使用 `TextureView` 或 `SurfaceView`。
│   │   │   │       │   │   │   #   - 扫描框 (View):  在预览画面上绘制一个扫描框，指示用户对准二维码。
│   │   │   │       │   │   │   #   - (Optional) 提示信息 (TextView):  例如 "请将二维码对准扫描框"。
│   │   │   │       │   │   │   #   - (Optional) 手动输入入口 (TextView 或 Button):  允许用户手动输入开门码。
│   │   │   │       │   ├── notification/         # 社区通知模块
│   │   │   │       │   │   ├── NotificationListActivity.java # 通知列表 Activity，负责展示通知列表
│   │   │   │       │   │   ├── NotificationDetailActivity.java # 通知详情 Activity，负责展示通知内容
│   │   │   │       │   │   ├── **activity_notification_list.xml** # 通知列表页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "社区通知"。
│   │   │   │       │   │   │   #   - 通知列表 (RecyclerView):
│   │   │   │       │   │   │   #       - 使用 `RecyclerView` 展示通知列表。
│   │   │   │       │   │   │   #       - 每个 Item 的布局 (`item_notification.xml`，需要创建):
│   │   │   │       │   │   │   #           - `TextView`: 通知标题。
│   │   │   │       │   │   │   #           - `TextView`: 发布时间。
│   │   │   │       │   │   │   #           - (Optional) `ImageView`: 通知图标。
│   │   │   │       │   │   ├── **activity_notification_detail.xml** # 通知详情页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "通知详情"。
│   │   │   │       │   │   │   #   - 内容区域 (ScrollView):
│   │   │   │       │   │   │   #       - `TextView`: 通知的完整标题。
│   │   │   │       │   │   │   #       - `TextView`: 发布时间。
│   │   │   │       │   │   │   #       - `TextView`: 通知正文内容。
│   │   │   │       │   │   │   #       - (Optional) `ImageView`:  通知图片。
│   │   │   │       │   ├── alarm/                # 报警记录模块
│   │   │   │       │   │   ├── AlarmRecordActivity.java # 报警记录 Activity，负责展示报警记录列表
│   │   │   │       │   │   ├── **activity_alarm_record.xml** # 报警记录页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "报警记录"。
│   │   │   │       │   │   │   #   - 报警记录列表 (RecyclerView):
│   │   │   │       │   │   │   #       - 使用 `RecyclerView` 展示报警记录。
│   │   │   │       │   │   │   #       - 每个 Item 的布局 (`item_alarm_record.xml`，需要创建):
│   │   │   │       │   │   │   #           - `TextView`: 报警时间。
│   │   │   │       │   │   │   #           - `TextView`: 报警类型。
│   │   │   │       │   │   │   #           - (Optional) `ImageView`: 报警状态图标。
│   │   │   │       │   ├── life_services/        # 生活服务模块
│   │   │   │       │   │   ├── HousekeepingServiceActivity.java # 家政服务 Activity
│   │   │   │       │   │   ├── DeliveryServiceActivity.java   # 快递服务 Activity
│   │   │   │       │   │   ├── **activity_housekeeping_service.xml** # 家政服务页面布局
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "家政服务"。
│   │   │   │       │   │   │   #   - 服务列表 (RecyclerView 或 ListView): 展示可预约的家政服务项目。
│   │   │   │       │   │   │   #   - (Optional) 搜索栏 (EditText): 用于搜索家政服务。
│   │   │   │       │   │   │   #   - 每个 Item 的布局 (`item_housekeeping_service.xml`，需要创建):
│   │   │   │       │   │   │   #       - `ImageView`: 服务图片。
│   │   │   │       │   │   │   #       - `TextView`: 服务名称。
│   │   │   │       │   │   │   #       - `TextView`: 服务价格。
│   │   │   │       │   │   │   #       - `Button`: "预约" 按钮。
│   │   │   │       │   │   ├── **activity_delivery_service.xml**   # 快递服务页面布局
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "快递服务"。
│   │   │   │       │   │   │   #   - 功能入口 (LinearLayout 或 GridView):
│   │   │   │       │   │   │   #       - `Button`: "快递查询"。
│   │   │   │       │   │   │   #       - `Button`: "代收点信息"。
│   │   │   │       │   │   │   #       - (Optional) `EditText`: 快递单号输入框 (用于查询)。
│   │   │   │       │   ├── setting/              # 设置模块
│   │   │   │       │   │   ├── SettingsActivity.java     # 设置 Activity，负责展示设置选项和处理设置操作
│   │   │   │       │   │   ├── **activity_settings.xml**     # 设置页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - 顶部工具栏 (Toolbar 或 AppBarLayout):
│   │   │   │       │   │   │   #       - 返回按钮。
│   │   │   │       │   │   │   #       - 标题 "设置"。
│   │   │   │       │   │   │   #   - 设置选项列表 (RecyclerView 或 ListView):
│   │   │   │       │   │   │   #       - 每个 Item 的布局 (`item_setting.xml`，需要创建):
│   │   │   │       │   │   │   #           - `ImageView`: 设置项图标。
│   │   │   │       │   │   │   #           - `TextView`: 设置项名称，例如 "修改密码"、"推送通知设置"、"关于我们"。
│   │   │   │       │   │   │   #           - (Optional) `Switch` 或 `CheckBox`: 用于切换设置状态。
│   │   │   │       │   │   │   #           - `ImageView`: 右箭头。
│   │   │   │       │   ├── login/                # 登录模块 (如果需要)
│   │   │   │       │   │   ├── LoginActivity.java        # 登录 Activity，负责处理用户登录逻辑
│   │   │   │       │   │   ├── **activity_login.xml**        # 登录页面的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - Logo 区域 (ImageView): 显示应用 Logo。
│   │   │   │       │   │   │   #   - 表单区域 (LinearLayout):
│   │   │   │       │   │   │   #       - `EditText`: 用户名/手机号输入框。
│   │   │   │       │   │   │   #       - `EditText`: 密码输入框 (`inputType="textPassword"`).
│   │   │   │       │   │   │   #       - (Optional) `CheckBox`: "记住密码"。
│   │   │   │       │   │   │   #   - 操作按钮区域 (LinearLayout):
│   │   │   │       │   │   │   #       - `Button`: "登录" 按钮。
│   │   │   │       │   │   │   #   - 底部链接区域 (LinearLayout):
│   │   │   │       │   │   │   #       - `TextView`: "忘记密码？" (可点击)。
│   │   │   │       │   │   │   #       - `TextView`: "注册账号" (可点击)。
│   │   │   │       │   ├── splash/               # 启动页模块
│   │   │   │       │   │   ├── SplashActivity.java       # 启动页 Activity，负责展示 Splash 界面和执行初始化操作
│   │   │   │       │   │   ├── **activity_splash.xml**       # 启动页 Activity 的布局文件
│   │   │   │       │   │   │   # 页面布局：
│   │   │   │       │   │   │   #   - `ImageView`: 显示应用 Logo (居中)。
│   │   │   │       │   │   │   #   - `TextView`: 显示应用名称 (在 Logo 下方)。
│   │   │   │       │   │   │   #   - (Optional) `ProgressBar`:  显示加载进度。
│   │   │   │       ├── utils/
│   │   │   │       │   ├── NetworkUtils.java
│   │   │   │       │   ├── DateUtils.java
│   │   │   │       ├── data/
│   │   │   │       │   ├── model/
│   │   │   │       │   │   ├── User.java
│   │   │   │       │   │   ├── Notification.java
│   │   │   │       │   │   ├── Alarm.java
│   │   │   │       │   ├── remote/
│   │   │   │       │   │   ├── ApiService.java
│   │   │   │       │   │   ├── RetrofitClient.java
│   │   │   │       │   ├── local/
│   │   │   │       │   │   ├── AppDatabase.java
│   │   │   │       │   │   ├── UserDao.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── ...
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml       # 主 Activity 的布局文件，包含底部导航栏容器
│   │   │   │   │   │   # 页面布局：
│   │   │   │   │   │   #   - 底部导航栏 (BottomNavigationView)：包含 "首页"、"开锁"、"我的" 三个 Tab。
│   │   │   │   │   │   #   - 用于显示 Fragment 的容器 (FrameLayout 或 NavHostFragment)：根据底部导航栏的点击切换不同的 Fragment。
│   │   │   │   │   ├── activity_home.xml
│   │   │   │   │   ├── activity_profile.xml
│   │   │   │   │   ├── activity_door_control.xml
│   │   │   │   │   ├── activity_intercom.xml
│   │   │   │   │   ├── activity_surveillance.xml
│   │   │   │   │   ├── activity_invite_visitor.xml
│   │   │   │   │   ├── activity_call_elevator.xml
│   │   │   │   │   ├── activity_scan_to_open.xml
│   │   │   │   │   ├── activity_notification_list.xml
│   │   │   │   │   ├── activity_notification_detail.xml
│   │   │   │   │   ├── activity_alarm_record.xml
│   │   │   │   │   ├── activity_housekeeping_service.xml
│   │   │   │   │   ├── activity_delivery_service.xml
│   │   │   │   │   ├── activity_settings.xml
│   │   │   │   │   ├── activity_login.xml
│   │   │   │   │   ├── activity_splash.xml
│   │   │   │   │   ├── item_quick_action.xml      # 快捷操作的 Item 布局 (ImageView, TextView)
│   │   │   │   │   ├── item_life_service.xml      # 生活服务的 Item 布局 (ImageView, TextView)
│   │   │   │   │   ├── item_notification.xml      # 通知列表的 Item 布局 (TextView, TextView, Optional ImageView)
│   │   │   │   │   ├── item_alarm_record.xml      # 报警记录的 Item 布局 (TextView, TextView, Optional ImageView)
│   │   │   │   │   ├── item_setting.xml         # 设置选项的 Item 布局 (ImageView, TextView, Optional Switch/CheckBox, ImageView)
│   │   │   │   ├── mipmap-anydpi-v26/
│   │   │   │   │   ├── ic_launcher.xml
│   │   │   │   │   ├── ic_launcher_round.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── themes.xml
│   │   │   │   │   ├── themes.xml (night)
│   │   │   │   ├── xml/
│   │   │   │   │   ├── backup_rules.xml
│   │   │   │   │   ├── data_extraction_rules.xml
│   │   │   └── test/
│   ├── build.gradle
│   ├── gradle.properties
│   ├── gradle/
│   │   └── wrapper/
│   ├── gradlew
│   ├── gradlew.bat
│   └── settings.gradle

```

