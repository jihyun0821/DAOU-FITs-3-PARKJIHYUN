### JavaFX 실행 준비

- `File` > `Project Structure` > `Libraries`
    - JavaFX SDK의 `lib` 폴더 추가
- `실행구성` > `VM arguments` 설정
    
    ![image.png](attachment:486b93ef-e36f-4ceb-ac23-86155c4b8c68:image.png)
    
    ```java
    --module-path "C:\Users\daou__journnie\Downloads\openjfx-21.0.6_windows-x64_bin-sdk\javafx-sdk-21.0.6\lib" --add-modules javafx.controls,javafx.fxml
    ```
    

![hierarchy-diagram.png](attachment:44c3da42-5cba-404e-82f8-b948751fe431:hierarchy-diagram.png)

### Delegation Event Model

![delegation-event-model-in-java.png](attachment:d908dbcc-b735-4521-89b1-f8a406f1d698:delegation-event-model-in-java.png)

- event source
    - 이벤트 발생 객체
- event object (자동생성)
    - 이벤트 세부정보
- event handler
    - 이벤트 처리 객체

## 1. 프로그램 시작 및 JavaFX 애플리케이션 초기화

- **`Main.main(String[] args)`**
    - 애플리케이션의 시작점입니다.
    - `launch(args)`를 호출하면 내부적으로 JavaFX 런타임이 초기화되고, 별도의 **JavaFX Application Thread**가 생성됩니다.
    - 이 과정에서 JavaFX 환경이 준비되고, 이후 `start(Stage primaryStage)` 메서드가 호출됩니다.

---

## 2. `start(Stage stage)` 메서드 실행 (기본(primary) 창 생성)

- **레이아웃 및 컴포넌트 구성**
    - `BorderPane root = new BorderPane();`
        - BorderPane은 화면을 동서남북과 중앙으로 구분하는 레이아웃 매니저입니다.
        - `root.setPrefSize(300, 300);`로 기본 크기를 지정합니다.
    - `Button btn = new Button("new window");`
        - 텍스트가 "new window"인 버튼을 생성합니다.
        - `btn.setPrefSize(300, 150);`로 버튼의 크기를 지정합니다.
- **버튼 이벤트 핸들러 등록**
    - `btn.setOnAction(e -> { ... });`
        - 사용자가 버튼을 클릭하면 이벤트가 발생합니다.
        - 이벤트 핸들러 내부에서 새로운 Stage를 생성하는데,
        
        를 통해 새로운 창(`MyStage`)을 생성하고 화면에 보여줍니다.
            
            ```java
            Stage myStage = new MyStage();
            myStage.show();
            
            ```
            
- **컴포넌트 배치 및 씬(Scene) 설정**
    - `root.setBottom(btn);`
        - 생성한 버튼을 BorderPane의 **하단(bottom)** 영역에 배치합니다.
    - `Scene scene = new Scene(root);`
        - BorderPane을 루트 노드로 갖는 씬을 생성합니다.
    - `stage.setScene(scene);`
        - 기본 Stage(창)에 생성한 씬을 설정합니다.
    - `stage.show();`
        - 기본 창을 화면에 표시합니다.

**결과**:

프로그램 실행 시 300×300 크기의 기본 창이 나타나고, 하단에는 300×150 크기의 "new window" 버튼이 보입니다.

---

## 3. 사용자가 "new window" 버튼 클릭 시 (새로운 창 생성)

- **버튼 클릭 이벤트**
    - 사용자가 기본 창의 "new window" 버튼을 클릭하면, 버튼의 onAction 핸들러가 호출됩니다.
    - 핸들러 내부에서 `new MyStage()`를 통해 새로운 Stage 객체를 생성합니다.
- **`MyStage` 생성자 실행**
    - **FXML 로드**
        - `FXMLLoader.load(getClass().getResource("sampleFX.fxml"));`를 호출하여 `sampleFX.fxml` 파일을 로드합니다.
        - 이 FXML 파일은 새로운 창의 UI 레이아웃을 정의하고 있으며,
        보통 `fx:controller` 속성을 통해 이 FXML 파일과 연결된 컨트롤러 클래스(여기서는 `MyStageController`)를 지정합니다.
        - FXMLLoader는 FXML 파일에 정의된 노드(컨트롤 및 레이아웃)를 생성하고, 컨트롤러 객체를 인스턴스화한 후,
        컨트롤러의 `initialize(URL, ResourceBundle)` 메서드를 호출합니다.
    - **씬(Scene) 생성 및 설정**
        - `Scene scene = new Scene(root);`
            - FXML에서 로드한 `root` 노드를 루트로 갖는 씬을 생성합니다.
        - `this.setScene(scene);`
            - 생성된 씬을 현재 Stage(새로운 창)에 설정합니다.
- **`myStage.show()` 호출**
    - 이벤트 핸들러에서 `myStage.show();`를 호출하여 새로운 창을 화면에 표시합니다.

**결과**:

"new window" 버튼을 클릭하면 FXML 파일에 정의된 UI가 나타나는 새 창이 열립니다.

---

## 4. FXML과 컨트롤러 (`MyStageController`)의 동작

- **FXML 로딩과 컨트롤러 연결**
    - FXML 파일(`sampleFX.fxml`)에는 보통 레이아웃과 함께 UI 컴포넌트(예: Button 등)가 정의되어 있고,
    각 컴포넌트에 `fx:id`를 부여해 컨트롤러에서 사용할 수 있도록 합니다.
    - `fx:controller="lecture.day6.javafxsimple.MyStageController"`와 같이 컨트롤러가 지정되어 있다면,
    FXMLLoader는 `MyStageController` 객체를 생성한 후 해당 객체의 `@FXML` 애노테이션이 붙은 필드와 FXML의 컴포넌트를 연결합니다.
- **컨트롤러의 `initialize` 메서드 호출**
    - `MyStageController`는 `Initializable` 인터페이스를 구현하고 있으므로,
    FXML 로드가 완료되면 `initialize(URL arg0, ResourceBundle arg1)` 메서드가 자동으로 호출됩니다.
    - 이 메서드 내부에서는
        - `System.out.println("Controller 생성!!");`를 통해 콘솔에 메시지를 출력하고,
        - `firstBtn`과 `secondBtn` 버튼의 onAction 이벤트 핸들러를 등록합니다.
            - 각각의 버튼을 클릭하면 "첫번째 버튼이 눌렸어요!" 또는 "두번째 버튼이 눌렸어요!"라는 메시지가 출력됩니다.

**결과**:

새 창이 열리면 FXML에 정의된 UI(예: 두 개의 버튼)가 보이고, 컨트롤러가 초기화되며 콘솔에 "Controller 생성!!" 메시지가 출력됩니다.

이후, 새 창의 버튼을 클릭하면 해당 이벤트 핸들러가 실행되어 콘솔에 메시지가 출력됩니다.

---

## 전체 실행 흐름 요약

1. **프로그램 시작**:
    - `Main.main()` → `launch(args)`
    - JavaFX 런타임이 초기화되고, `start(Stage primaryStage)`가 호출됨.
2. **기본 창 생성**:
    - BorderPane 레이아웃과 "new window" 버튼을 생성하여 기본 Stage에 Scene으로 설정하고 보여줌.
3. **새 창 생성**:
    - 사용자가 "new window" 버튼 클릭 시, 이벤트 핸들러가 실행되어
    새로운 `MyStage` 객체를 생성하고 `show()`를 호출함.
4. **FXML 로드 및 컨트롤러 초기화**:
    - `MyStage` 생성자 내에서 `sampleFX.fxml`을 로드하여 UI를 구성하고,
    FXML에 지정된 `MyStageController`가 생성되어 `initialize()`가 호출됨.
5. **새 창의 동작**:
    - 새 창에 FXML로 정의된 UI가 표시되고,
    컨트롤러의 이벤트 핸들러를 통해 새 창의 버튼 클릭 시 콘솔에 메시지 출력됨.

---

### DO (Domain Object)

Domain에서 사용하는 데이터를 객체화 시킨 것

### VO (Value Object)

각 table의 하나의 row를 담을 수 있는 객체

### Entity

VO와 유사 그런데 PK 포함

### DTO (Data Transfer Object)

== VO

데이터의 전달을 주 목적으로 하는 VO
