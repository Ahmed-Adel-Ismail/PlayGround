package com.tere.samples;

@SuppressWarnings("all")
public class UseCaseTest {
//    @Test
//    public void main() throws Exception {
//        UseCase useCase = new UseCase();
//        useCase.api.getQuestions()
//                .flatMap(toQuestion())
//                .doOnNext(printQuestion())
//                .flatMap(toAnswersListWrapper(useCase))
//                .flatMap(toAnswer())
//                .blockingSubscribe(printAnswer(),printError());
//    }
//
//    private Predicate<Answer> byAcceptedAnswer() {
//        return new Predicate<Answer>() {
//            @Override
//            public boolean test(@NonNull Answer answer) throws Exception {
//                return answer.accepted;
//            }
//        };
//    }
//
//    private Function<ListWrapper<Question>, ObservableSource<Question>> toQuestion() {
//        return new Function<ListWrapper<Question>, ObservableSource<Question>>() {
//            @Override
//            public ObservableSource<Question> apply(@NonNull ListWrapper<Question> questionsWrapper) {
//                return Observable.fromIterable(questionsWrapper.items);
//            }
//        };
//    }
//
//    private Consumer<Question> printQuestion() {
//        return new Consumer<Question>() {
//            @Override
//            public void accept(Question question) throws Exception {
//                System.out.println("Question : " + question);
//                System.out.println("------------");
//            }
//        };
//    }
//
//    private Consumer<Answer> printAnswer() {
//        return new Consumer<Answer>() {
//            @Override
//            public void accept(Answer answer) throws Exception {
//                System.out.println(answer);
//                System.out.println("======");
//            }
//        };
//    }
//
//
//
//    @android.support.annotation.NonNull
//    private Function<ListWrapper<Answer>, ObservableSource<Answer>> toAnswer() {
//        return new Function<ListWrapper<Answer>, ObservableSource<Answer>>() {
//            @Override
//            public ObservableSource<Answer> apply(@NonNull ListWrapper<Answer> answerListWrapper) throws Exception {
//                return Observable.fromIterable(answerListWrapper.items);
//            }
//        };
//    }
//
//
//
//
//    @android.support.annotation.NonNull
//    private Function<Question, ObservableSource<ListWrapper<Answer>>> toAnswersListWrapper(final UseCase useCase) {
//        return new Function<Question, ObservableSource<ListWrapper<Answer>>>() {
//            @Override
//            public ObservableSource<ListWrapper<Answer>> apply(@NonNull Question question) throws Exception {
//                printQuestion().accept(question);
//                return useCase.api.getAnswersForQuestion(question.questionId);
//            }
//        };
//    }
//
//    private Consumer<ListWrapper<Question>> printQuestionsList() {
//        return new Consumer<ListWrapper<Question>>() {
//            @Override
//            public void accept(@NonNull ListWrapper<Question> questionListWrapper) {
//                for (Question question : questionListWrapper.items) {
//                    System.out.println(question);
//                }
//            }
//        };
//    }
//
//    private Consumer<? super Throwable> printError() {
//        return new Consumer<Throwable>() {
//            @Override
//            public void accept(@NonNull Throwable throwable) {
//                throwable.printStackTrace();
//            }
//        };
//    }
//

}