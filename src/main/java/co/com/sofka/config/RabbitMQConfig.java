package co.com.sofka.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // @Configuration =  permiten enfocar de otra forma que se encargue de realizar la configuración de la
// aplicación
public class RabbitMQConfig {
    // @Value = para inicializar las propiedades
    @Value("${javainuse.rabbitmq.queue}")
    String queueName;

    @Value("${javainuse.rabbitmq.exchange}")
    String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    @Bean // @Bean = para ser administrado por el contenedor de Spring. Es una anotación a nivel de método. Durante la
        // configuración de Java ( @Configuration) Cuando JavaConfig encuentra un método de este tipo, ejecutará ese
        // método y registrará el valor de retorno como un bean dentro de un BeanFactory. De forma predeterminada, el
        // nombre del bean será el mismo que el nombre del método
    Queue queue() { // queue() = método crea una cola AMQP
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange directExchange() { // DirectExchange = método crea un intercambio de temas.
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) { // binding() = método une estos dos, definiendo el comportamiento que
        // ocurre cuando se RabbitTemplatepublica en un intercambio.
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }


    @Bean
    // AmqpTemplate = Especifica un conjunto básico de operaciones AMQP. Proporciona métodos de envío y recepción sincrónicos.
    // Los métodos convertAndSend(Object)y receiveAndConvert()permiten enviar y recibir objetos POJO. Se espera
    // que las implementaciones se deleguen en una instancia de MessageConverterpara realizar la conversión hacia y
    // desde el tipo de carga útil del byte [] de AMQP.
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) { // ConnectionFactory
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //rabbitTemplate.setMessageConverter(messageConverter()); // rabbitTemplate = ofrece todo lo que necesita
        // para enviar y recibir mensajes con RabbitMQ.
        return rabbitTemplate;
    }
}
